package com.mvvm.data.repo.repo

import androidx.lifecycle.LiveData
import com.mvvm.data.repo.db.LocalDataSource
import com.mvvm.data.repo.extentions.AbsentLiveData
import com.mvvm.data.repo.model.Result
import com.mvvm.data.repo.network.RemoteNetworkSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(var dbSource: LocalDataSource, var remoteSource: RemoteNetworkSource) {

    companion object

    var TAG = this.javaClass.name

    suspend fun getLatestMovies(): LiveData<List<Result>> {

        return withContext(Dispatchers.IO) {
            val dbResult = fetchLatestMoviesFromRemoteOrLocal()

            return@withContext dbResult
        }
    }

    private suspend fun fetchLatestMoviesFromRemoteOrLocal(): LiveData<List<Result>> {
        val localDBMovies = dbSource.getLatestMovies()
        localDBMovies.value?.let {
            if (it.isNotEmpty()) return localDBMovies
            else {
                val remoteData = remoteSource.getLatestMovies()
                if (remoteData.isSuccessful!!) {
                    val results = remoteData.body()?.results
                    results?.let {
                        dbSource.insertLatestMovies(results)
                        return dbSource.getLatestMovies()
                    }
                }
            }
        }
        return AbsentLiveData.create()
    }

    suspend fun getTopMovies(): LiveData<List<Result>> {
        return withContext(Dispatchers.IO) {
            return@withContext fetchTopMovies()
        }
    }

    private suspend fun fetchTopMovies(): LiveData<List<Result>> {
        return withContext(Dispatchers.IO) {
            val topMovies = dbSource.getTopMovies()
            val localDBMovies = topMovies.value?.isEmpty() ?: false

            if (localDBMovies) return@withContext topMovies
            else {
                val remoteData = remoteSource.getTopMovies()
                if (remoteData.isSuccessful!!) {
                    val results = remoteData.body()?.results
                    results?.let {
                        dbSource.insertTopMovies(results)
                        return@withContext dbSource.getTopMovies()
                    }
                }


            }
            return@withContext AbsentLiveData.create<List<Result>>()
        }

    }
}

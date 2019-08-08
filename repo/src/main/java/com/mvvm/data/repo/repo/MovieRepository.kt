package com.mvvm.data.repo.repo

import android.content.ContentValues.TAG
import android.util.Log
import com.mvvm.data.repo.db.LocalDataSource
import com.mvvm.data.repo.model.Result
import com.mvvm.data.repo.network.RemoteNetworkSource
import com.mvvm.data.repo.result.DBResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(
    private var dbSource: LocalDataSource,
    private var remoteSource: RemoteNetworkSource
) {

    suspend fun getLatestMovies(): DBResult<List<Result>> {

        return withContext(Dispatchers.IO) {
            return@withContext fetchLatestMoviesFromRemoteOrLocal()
        }
    }

    private suspend fun fetchLatestMoviesFromRemoteOrLocal(): DBResult<List<Result>> {
        when (val remoteMovies = dbSource.getLatestMovies()) {
            is DBResult.Error -> Log.i(TAG, "Remote data source fetch failed")
            is DBResult.Success -> {
                if (remoteMovies.data.isNotEmpty())
                    return remoteMovies
            }
            else -> throw IllegalStateException()
        }
        val networkData = remoteSource.getLatestMovies()
        if (networkData.isSuccessful) {
            val results = networkData.body()?.results
            results?.let {
                dbSource.insertLatestMovies(results)
                return dbSource.getLatestMovies()
            }
        }
        return DBResult.Error(Exception("Error fetching from remote and local"))
    }

    suspend fun getTopMovies(): DBResult<List<Result>> {
        return withContext(Dispatchers.IO) {
            return@withContext fetchTopMovies()
        }
    }

    private suspend fun fetchTopMovies(): DBResult<List<Result>> {
        when (val remoteMovies = dbSource.getTopMovies()) {
            is DBResult.Error -> Log.i(TAG, "Remote data source fetch failed")
            is DBResult.Success -> {
                if (remoteMovies.data.isNotEmpty())
                    return remoteMovies
            }
            else -> throw IllegalStateException()
        }
        val networkData = remoteSource.getTopMovies()
        if (networkData.isSuccessful) {
            val results = networkData.body()?.results
            results?.let {
                dbSource.insertTopMovies(results)
                return dbSource.getTopMovies()
            }
        }
        return DBResult.Error(Exception("Error fetching from remote and local"))
    }

}

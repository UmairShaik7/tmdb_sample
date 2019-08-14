package com.mvvm.data.repo.repo

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import com.mvvm.data.repo.AppConstants
import com.mvvm.data.repo.db.LocalDataSource
import com.mvvm.data.repo.extentions.mapInPlace
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
        when (val localDBMovies = dbSource.getLatestMovies()) {
            is DBResult.Error -> Log.i(TAG, "Remote data source fetch failed")
            is DBResult.Success -> {
                if (localDBMovies.data.isNotEmpty())
                    return localDBMovies
            }
            else -> throw IllegalStateException()
        }
        val remoteNetworkData = remoteSource.getLatestMovies()
        if (remoteNetworkData.isSuccessful) {
            val results = remoteNetworkData.body()?.results
            results?.let {
                /* dbSource.insertLatestMovies(results.filter { result -> !result.poster_path.isNullOrEmpty() }.also {
                     results.mapInPlace {
                         it.copy(category = AppConstants.MovieCategories.LATEST_MOVIES.type)
                     }
                 })*/
                results.filter { result -> !result.poster_path.isNullOrEmpty() }
                results.mapInPlace { it.copy(category = AppConstants.MovieCategories.LATEST_MOVIES.type) }
                dbSource.insertLatestMovies(results)

                return dbSource.getLatestMovies()
            }
        }
        return DBResult.Error(Exception("Error fetching from remote and local"))
    }

    suspend fun getTopMovies(): DBResult<List<Result>> = withContext(Dispatchers.IO) {
        return@withContext fetchTopMovies()
    }


    private suspend fun fetchTopMovies(): DBResult<List<Result>> {
        when (val localDBMovies = dbSource.getTopMovies()) {
            is DBResult.Error -> Log.i(TAG, "Remote data source fetch failed")
            is DBResult.Success -> {
                if (localDBMovies.data.isNotEmpty())
                    return localDBMovies
            }
            else -> throw IllegalStateException()
        }
        val remoteNetworkData = remoteSource.getTopMovies()
        if (remoteNetworkData.isSuccessful) {
            val results = remoteNetworkData.body()?.results
            results?.let { list ->
                list.mapInPlace {
                    it.copy(category = AppConstants.MovieCategories.TOP_MOVIES.type)

                }
                dbSource.insertTopMovies(list)
                return dbSource.getTopMovies()
            }
        }
        return DBResult.Error(Exception("Error fetching from remote and local"))
    }

    suspend fun getGenreMovies(genreType: String): DBResult<List<Result>> =
        withContext(Dispatchers.IO) {
            val code = AppConstants.Genre.valueOf(genreType).code.toInt()
            when (val localDbResults = dbSource.getMoviesWithGenre()) {
                is DBResult.Success -> {
                    val item = localDbResults.data
                    return@withContext DBResult.Success(item.filter { result ->
                        result.genre_ids?.contains(code) ?: false
                    })

                }
                else -> { //TODO network query
                    return@withContext localDbResults
                }
            }

        }


    fun getLatestMoviesLiveData(): LiveData<List<Result>> = dbSource.getLatestMovies2()


}

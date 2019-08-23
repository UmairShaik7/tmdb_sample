package com.mvvm.data.repo.db

import com.mvvm.data.repo.AppConstants
import com.mvvm.data.repo.model.Result
import com.mvvm.data.repo.result.DBResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Concrete implementation of a data source as a db.
 */
class LocalDataSource(val db: DBService) {

    suspend fun getLatestMovies(): DBResult<List<Result>> = withContext(Dispatchers.IO) {
        return@withContext try {
            DBResult.Success(
                db.database.resultDao().getMovies(AppConstants.MovieCategories.LATEST_MOVIES.type)
            )
        } catch (e: Exception) {
            DBResult.Error(e)
        }
    }


    suspend fun insertTopMovies(results: List<Result>?) = withContext(Dispatchers.IO) {
        db.database.resultDao().saveMovies(results)
    }


    suspend fun getTopMovies(): DBResult<List<Result>> = withContext(Dispatchers.IO) {
        return@withContext try {
            DBResult.Success(db.database.resultDao().getMovies(AppConstants.MovieCategories.TOP_MOVIES.type))
        } catch (e: Exception) {
            DBResult.Error(e)
        }
    }

    suspend fun insertLatestMovies(results: List<Result>) = withContext(Dispatchers.IO) {
        db.database.resultDao().saveMovies(results)
    }

    suspend fun getAllMovies(): DBResult<List<Result>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                DBResult.Success(db.database.resultDao().getAll())
            } catch (e: Exception) {
                DBResult.Error(e)
            }
        }

    suspend fun getLatestMoviesLiveData() = withContext(Dispatchers.IO) {
        db.database.resultDao().getMoviesLiveData(AppConstants.MovieCategories.LATEST_MOVIES.type)
    }


    fun getGenreMovies(it: String) = db.database.resultDao().getMoviesWithGenra(it)

    suspend fun getMovie(movieId: Int) = withContext(Dispatchers.IO) {
        DBResult.Success(db.database.resultDao().getMovie(movieId))

    }


}



package com.mvvm.data.repo.repo

import com.mvvm.data.repo.model.MoviesResponse
import com.mvvm.data.repo.service.Services
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(val service: Services) {

    val api = service.api
    suspend fun getLatestMovies(): List<MoviesResponse>? {

        return withContext(Dispatchers.IO) {
            val result = api.getLatestMovies()
            if (result.isSuccessful) {
                return@withContext result.body()?.results
            } else return@withContext emptyList<MoviesResponse>()
        } as List<MoviesResponse>?
    }

}

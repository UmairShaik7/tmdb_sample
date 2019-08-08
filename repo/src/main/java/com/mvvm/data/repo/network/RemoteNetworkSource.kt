package com.mvvm.data.repo.network

import com.mvvm.data.repo.model.MoviesResponse
import com.mvvm.data.repo.service.Services
import retrofit2.Response

class RemoteNetworkSource(val service: Services) {
    val network = service.api

    suspend fun getLatestMovies(): Response<MoviesResponse> {
        return network.getLatestMovies()
    }

    suspend fun getTopMovies(): Response<MoviesResponse> {
        return network.getTopMovies()

    }

}
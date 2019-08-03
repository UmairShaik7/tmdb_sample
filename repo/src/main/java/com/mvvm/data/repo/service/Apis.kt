package com.mvvm.data.repo.service

import com.mvvm.data.repo.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface Apis {

    @GET("movie/latest")
    suspend fun getLatestMovies(): Response<MoviesResponse>


}
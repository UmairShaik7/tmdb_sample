package com.mvvm.data.repo.service

import com.mvvm.data.repo.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface Apis {

    @GET("movie/now_playing")
    suspend fun getLatestMovies(): Response<MoviesResponse>


    @GET("movie/top_rated")
    suspend fun getTopMovies(): Response<MoviesResponse>


}
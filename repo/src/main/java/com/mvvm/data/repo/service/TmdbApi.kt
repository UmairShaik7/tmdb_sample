package com.mvvm.data.repo.service

import com.mvvm.data.repo.model.TmdbMovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getPopularMovie(): Response<TmdbMovieResponse>
}
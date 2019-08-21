package com.mvvm.data.repo.service

import com.mvvm.data.repo.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Apis {

    @GET("movie/now_playing")
    suspend fun getLatestMovies(): Response<MoviesResponse>


    @GET("movie/top_rated")
    suspend fun getTopMovies(): Response<MoviesResponse>

    @GET("discover/movie")//?sort_by=release_date.asc")
    suspend fun getGenra(@Query("with_genres") item: String, @Query("page") page: Int): Response<MoviesResponse>

    @GET("search/movie")
    suspend fun getSearchResults(@Query("query") searchQuery: String, @Query("page") lastPageResult: Int): Response<MoviesResponse>


}
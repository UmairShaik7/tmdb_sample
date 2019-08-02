package com.mvvm.data.repo.service

import com.mvvm.data.repo.AppConstants

object Services{

    val tmdbApi : TmdbApi = RetrofitFactory.retrofit(AppConstants.TMDB_BASE_URL)
        .create(TmdbApi::class.java)
}
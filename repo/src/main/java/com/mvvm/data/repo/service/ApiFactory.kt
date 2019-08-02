package com.mvvm.data.repo.service

import com.mvvm.data.repo.AppConstants
import io.navendra.retrofitkotlindeferred.AppConstants

object ApiFactory{

    val placeholderApi : PlaceholderApi = RetrofitFactory.retrofit(AppConstants.JSON_PLACEHOLDER_BASE_URL)
                                                .create(PlaceholderApi::class.java)

    val tmdbApi : TmdbApi = RetrofitFactory.retrofit(AppConstants.TMDB_BASE_URL)
        .create(TmdbApi::class.java)
}
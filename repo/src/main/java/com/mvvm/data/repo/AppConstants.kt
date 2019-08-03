package com.mvvm.data.repo

import com.mvvm.data.BuildConfig

object AppConstants {
    const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    var tmdbApiKey = BuildConfig.TMDB_API_KEY
}
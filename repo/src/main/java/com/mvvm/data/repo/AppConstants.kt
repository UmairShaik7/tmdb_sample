package com.mvvm.data.repo

import com.mvvm.data.BuildConfig

object AppConstants {

    const val DATABASE_PAGE_SIZE = 10
    const val DEFAULT_LANGUAGE = "en-US"
    const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_PATH = "https://image.tmdb.org/t/p/w154"
    const val API_KEY = BuildConfig.TMDB_API_KEY
    const val YYYYMMDD = "yyyy-MM-dd"

    enum class Genre(val code: String) {
        ACTION("28"),
        ANIMATED("16"),
        DOCUMENTARY("99"),
        DRAMA("18"),
        FAMILY("10751"),
        FANTASY("14"),
        HISTORY("36"),
        COMEDY("35"),
        WAR("10752"),
        CRIME("80"),
        MUSIC("10402"),
        MYSTERY("9648"),
        ROMANCE("10749"),
        SCI_FI("878"),
        HORROR("27"),
        TV_MOVIE("10770"),
        THRILLER("53"),
        WESTERN("37"),
        ADVENTURE("12"),
    }

    enum class MovieCategories(val type: Int) {
        OTHER(0),
        TOP_MOVIES(1),
        LATEST_MOVIES(2)
    }
}
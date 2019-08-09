package com.mvvm.data.repo.service

import com.mvvm.data.repo.AppConstants

class Services(val retro: RetrofitFactory) {

    val api: Apis = retro.retrofit<RetrofitFactory>(baseUrl = AppConstants.TMDB_BASE_URL)
        .create(Apis::class.java)


}
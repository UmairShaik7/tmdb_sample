package com.mvvm.data.repo.service

import com.mvvm.data.repo.AppConstants
import com.mvvm.data.repo.db.DBService

class Services(val retro: RetrofitFactory) {

    val api: Apis = retro.retrofit<RetrofitFactory>(baseUrl = AppConstants.TMDB_BASE_URL).create(Apis::class.java)


}
package com.mvvm.data

import com.mvvm.data.repo.db.DBService
import com.mvvm.data.repo.db.LocalDataSource
import com.mvvm.data.repo.network.RemoteNetworkSource
import com.mvvm.data.repo.repo.MovieRepositoryImpl
import com.mvvm.data.repo.service.RetrofitFactory
import com.mvvm.data.repo.service.Services
import org.koin.dsl.module

val repoModule = module {

    //db
    single { DBService(get()) }

    //db layer
    single { LocalDataSource(get()) }

    //retrofit
    single { RetrofitFactory() }

    //service
    single { Services(get()) }

    //network
    single { RemoteNetworkSource(get()) }

    // single instance of Repo
    single { MovieRepositoryImpl(get(), get()) }

}


package com.mvvm.tmdb

import com.mvvm.data.repo.db.DBService
import com.mvvm.data.repo.repo.MovieRepositoryImpl
import com.mvvm.data.repo.service.RetrofitFactory
import com.mvvm.data.repo.service.Services
import com.mvvm.tmdb.ui.home.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //db
    single { DBService(get()) }

    //retrofit
    single { RetrofitFactory() }

    //service
    single { Services(get(), get()) }


    // single instance of Repo
    single { MovieRepositoryImpl(get()) }

    // MyViewModel ViewModel
    viewModel { HomeFragmentViewModel(get()) }
}


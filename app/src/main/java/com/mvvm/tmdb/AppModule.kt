package com.mvvm.tmdb

import com.mvvm.data.repo.repo.MovieRepository
import com.mvvm.data.repo.repo.MovieRepositoryImpl
import com.mvvm.tmdb.ui.home.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


    // single instance of Repo
    single<MovieRepository> { MovieRepositoryImpl() }

    // MyViewModel ViewModel
    viewModel { HomeFragmentViewModel(get()) }

}
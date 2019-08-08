package com.mvvm.tmdb

import com.mvvm.tmdb.ui.genre.GenreViewModel
import com.mvvm.tmdb.ui.home.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // MyViewModel ViewModel
    viewModel { HomeFragmentViewModel(get()) }

    viewModel { GenreViewModel(get()) }
}


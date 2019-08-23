package com.mvvm.tmdb

import com.mvvm.tmdb.ui.details.MovieDetailsViewModel
import com.mvvm.tmdb.ui.search.SearchViewModel
import com.mvvm.tmdb.ui.genre.GenreViewModel
import com.mvvm.tmdb.ui.home.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeFragmentViewModel(get()) }

    viewModel { GenreViewModel(get()) }
    viewModel { SearchViewModel(get()) }

    viewModel { MovieDetailsViewModel(get()) }
}


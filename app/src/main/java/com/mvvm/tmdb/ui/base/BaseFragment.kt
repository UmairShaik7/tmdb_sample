package com.mvvm.tmdb.ui.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract fun showMovieDetails(movieId: Int)
}
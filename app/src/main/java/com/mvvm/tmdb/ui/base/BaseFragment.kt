package com.mvvm.tmdb.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment() : Fragment() {
    abstract fun getMovieDetails(movieId:Int)
}
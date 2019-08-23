package com.mvvm.tmdb.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.mvvm.data.repo.repo.MovieRepository
import com.mvvm.tmdb.Event

abstract class BaseViewModel(private val repo: MovieRepository) : ViewModel() {

    var getMovieWithID = MutableLiveData<Int>()

    val movieDetails = getMovieWithID.switchMap { it -> repo.getMovieDetails(it) }

    val itemClick = MutableLiveData<Event<Int>>()
    open fun movieOnclick(movieId: Int) {
        itemClick.value = Event(movieId)
    }
}
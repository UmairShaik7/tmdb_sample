package com.mvvm.tmdb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.data.repo.model.Result
import com.mvvm.data.repo.repo.MovieRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeFragmentViewModel(val repo: MovieRepository) : ViewModel() {

    var latestMoviesResult: LiveData<List<Result>>? = null
    var topMoviesResult: LiveData<List<Result>>? = null

    fun getMovies(): Job = viewModelScope.launch {
        latestMoviesResult = repo.getLatestMovies()

        topMoviesResult = repo.getTopMovies()

    }

}

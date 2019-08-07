package com.mvvm.tmdb.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.data.repo.model.Result
import com.mvvm.data.repo.repo.MovieRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeFragmentViewModel(val repo: MovieRepository) : ViewModel() {

    val latestMoviesResult = MutableLiveData<List<Result>>().apply { value = emptyList() }
    val topMoviesResult = MutableLiveData<List<Result>>().apply { value = emptyList() }

    fun getMovies(): Job = viewModelScope.launch {
        latestMoviesResult.value = repo.getLatestMovies().value

        topMoviesResult.value = repo.getTopMovies().value

    }

}

package com.mvvm.tmdb.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.data.repo.model.Result
import com.mvvm.data.repo.repo.MovieRepositoryImpl
import com.mvvm.data.repo.result.DBResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeFragmentViewModel(val repo: MovieRepositoryImpl) : ViewModel() {

    val latestMoviesResult = MutableLiveData<List<Result>>().apply { value = emptyList() }
    val topMoviesResult = MutableLiveData<List<Result>>().apply { value = emptyList() }

    fun getMovies(): Job = viewModelScope.launch {
        val latestMovieResults = repo.getLatestMovies()
        if (latestMovieResults is DBResult.Success) {
            latestMoviesResult.value = latestMovieResults.data
            //Log.i("TAG",latestMovieResults.data[0].toString())
        }

        val topMovieResults: DBResult<List<Result>> = repo.getTopMovies()
        if (topMovieResults is DBResult.Success) {
            topMoviesResult.value = topMovieResults.data
        }

    }

}

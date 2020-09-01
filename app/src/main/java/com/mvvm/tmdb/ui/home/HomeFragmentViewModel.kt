package com.mvvm.tmdb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mvvm.data.repo.AppConstants
import com.mvvm.data.repo.model.Result
import com.mvvm.data.repo.repo.MovieRepository
import com.mvvm.data.repo.result.DBResult
import com.mvvm.tmdb.Event
import com.mvvm.tmdb.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeFragmentViewModel(private val repo: MovieRepository) : BaseViewModel(repo) {

    var latestMoviesResult :LiveData<List<Result>> =liveData { emit(repo.getLatestMoviesLiveData()) }
        //liveData { emit(repo.getLatestMoviesLiveData()) }
    val topMoviesResult = MutableLiveData<List<Result>>().apply { value = emptyList() }
    val genreResult = MutableLiveData<Array<AppConstants.Genre>>().apply {
        value = enumValues()
    }
    val genreLiveData = MutableLiveData<Event<String>>()

    fun getMovies(): Job = viewModelScope.launch {
        val topMovieResults: DBResult<List<Result>> = repo.getTopMovies()
        if (topMovieResults is DBResult.Success) {
            topMoviesResult.value = topMovieResults.data
        }
    }

    fun genreOnclick(name: String) {
        genreLiveData.value = Event(name)

    }

}

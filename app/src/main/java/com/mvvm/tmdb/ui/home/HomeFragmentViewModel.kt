package com.mvvm.tmdb.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mvvm.data.repo.AppConstants
import com.mvvm.data.repo.model.Result
import com.mvvm.data.repo.repo.MovieRepository
import com.mvvm.data.repo.result.DBResult
import com.mvvm.tmdb.Event
import com.mvvm.tmdb.ui.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeFragmentViewModel(private val repo: MovieRepository) : BaseViewModel() {

    val latestMoviesResult = repo.getLatestMoviesLiveData()
    val topMoviesResult = MutableLiveData<List<Result>>().apply { value = emptyList() }
    val genreResult = MutableLiveData<Array<AppConstants.Genre>>().apply {
        value = enumValues()
    }
    val genreLiveData = MutableLiveData<Event<String>>()

    fun getMovies(): Job = viewModelScope.launch {
       /* val latestMovieResults = repo.getLatestMovies()
        if (latestMovieResults is DBResult.Success) {
            //latestMoviesResult.value = latestMovieResults.data
        }*/

        val topMovieResults: DBResult<List<Result>> = repo.getTopMovies()
        if (topMovieResults is DBResult.Success) {
            topMoviesResult.value = topMovieResults.data
        }

    }

    fun genreOnclick(name: String) {
        genreLiveData.value = Event(name)

    }

}

package com.mvvm.tmdb.ui.genre

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.data.repo.model.Result
import com.mvvm.data.repo.repo.MovieRepository
import com.mvvm.data.repo.result.DBResult
import com.mvvm.tmdb.ui.BaseViewModel
import kotlinx.coroutines.launch

class GenreViewModel(private val repo: MovieRepository) : BaseViewModel() {


    private val localDBResults = MutableLiveData<List<Result>>().apply { value = emptyList() }

    fun getMoviesWithGenre(genreType: String) = viewModelScope.launch {
        val results = repo.getGenreMovies(genreType)
        if (results is DBResult.Success) {
            localDBResults.value = results.data
        }

    }


}

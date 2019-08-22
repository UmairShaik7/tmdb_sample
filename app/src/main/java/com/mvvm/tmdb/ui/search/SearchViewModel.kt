package com.mvvm.tmdb.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.mvvm.data.repo.repo.MovieRepository
import com.mvvm.tmdb.ui.BaseViewModel

class SearchViewModel(private val repo: MovieRepository) : BaseViewModel() {
    private val queryValue = MutableLiveData<String>()

    val posts = Transformations.switchMap(queryValue) { repo.searchMovie(it) }

    fun searchMovies(query: String) {
        if (queryValue.value != query && query.isNotBlank()) {
            queryValue.value = query
        }
    }

}
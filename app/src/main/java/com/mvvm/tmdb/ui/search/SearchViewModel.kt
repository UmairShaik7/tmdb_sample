package com.mvvm.tmdb.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.mvvm.data.repo.repo.MovieRepository
import com.mvvm.tmdb.ui.base.BaseViewModel

class SearchViewModel(private val repo: MovieRepository) : BaseViewModel(repo) {
    private val queryValue = MutableLiveData<String>()

    val posts = Transformations.switchMap(queryValue) { repo.searchMovie(it) }

    fun searchMovies(query: String) {
        if (queryValue.value != query && query.isNotBlank()) {
            queryValue.value = query
        }
    }

    override fun movieOnclick(movieId: Int) {
        posts.value?.filter { it -> it.id == movieId }?.let { it1 -> repo.insertMovie(it1) }
        super.movieOnclick(movieId)
    }


}
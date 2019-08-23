package com.mvvm.tmdb.ui.genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.paging.PagedList
import com.mvvm.data.repo.model.Result
import com.mvvm.data.repo.repo.MovieRepository
import com.mvvm.tmdb.ui.base.BaseViewModel


class GenreViewModel(private val repo: MovieRepository) : BaseViewModel(repo) {

    private val queryLiveData = MutableLiveData<String>()
   /* private val repoResult: LiveData<LiveData<PagedList<Result>>> = queryLiveData.map {
        repo.getGenreMoviesLiveData(it)
    }*/

    val repos: LiveData<PagedList<Result>> = queryLiveData.switchMap { it ->
        repo.getGenreMoviesLiveData(it)
    }

    fun setGenre(genreType: String) {
        queryLiveData.postValue(genreType)
    }

}



package com.mvvm.tmdb.ui.genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.mvvm.data.repo.model.Result
import com.mvvm.data.repo.repo.MovieRepository
import com.mvvm.tmdb.ui.BaseViewModel


class GenreViewModel(private val repo: MovieRepository) : BaseViewModel() {
    fun setGenre(genreType: String) {
        queryLiveData.postValue(genreType)
    }


    private val queryLiveData = MutableLiveData<String>()
    private val repoResult: LiveData<LiveData<PagedList<Result>>> =
        Transformations.map(queryLiveData) {
            repo.getGenreMoviesLiveData(it)
        }

    val repos: LiveData<PagedList<Result>> = Transformations.switchMap(repoResult) {
        it
    }
}



package com.mvvm.tmdb.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.mvvm.data.repo.repo.MovieRepository
import com.mvvm.tmdb.ui.base.BaseViewModel

class MovieDetailsViewModel(private val repo: MovieRepository) : BaseViewModel(repo) {
   /* var getMovieWithID = MutableLiveData<Int>()

    val movieDetails= getMovieWithID.switchMap { it-> repo.getMovieDetails(it) }
*/

}

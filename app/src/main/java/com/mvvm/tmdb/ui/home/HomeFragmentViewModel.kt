package com.mvvm.tmdb.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.data.repo.repo.MovieRepositoryImpl
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeFragmentViewModel(val repo: MovieRepositoryImpl) : ViewModel() {

    val item = MutableLiveData<Any>()

    fun getMovies(): Job = viewModelScope.launch {
        val result = repo.getLatestMovies()
        item.value = result
    }


}

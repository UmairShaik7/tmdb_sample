package com.mvvm.data.repo.repo

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mvvm.data.repo.db.LocalDataSource
import com.mvvm.data.repo.model.Result
import com.mvvm.data.repo.network.RemoteNetworkSource

class MovieDataSourceFactory(
    private val remoteSource: RemoteNetworkSource,
    private val searchQuery: String
) : DataSource.Factory<Int, Result>() {

    val sourceLiveData = MutableLiveData<PageKeyedMovieDataSource>()
    override fun create(): DataSource<Int, Result> {
        val source = PageKeyedMovieDataSource(remoteSource, searchQuery)
        sourceLiveData.postValue(source)
        return source
    }

}

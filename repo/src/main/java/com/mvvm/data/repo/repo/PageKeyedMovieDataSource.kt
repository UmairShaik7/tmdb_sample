package com.mvvm.data.repo.repo

import androidx.paging.PageKeyedDataSource
import com.mvvm.data.repo.model.Result
import com.mvvm.data.repo.network.RemoteNetworkSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class PageKeyedMovieDataSource(
    private val remoteSource: RemoteNetworkSource,
    private val searchQuery: String
) : PageKeyedDataSource<Int, Result>() {

    private var lastPageResult = 1
    private var isRequestInProgress = false


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        if (isRequestInProgress) return
        loadData(callback)
    }

    private fun loadData(callback: LoadInitialCallback<Int, Result>) {
        if (isRequestInProgress) return
        isRequestInProgress = true
        runBlocking(Dispatchers.IO) {
            val response = remoteSource.getSearchResults(searchQuery, lastPageResult)
            val data = response.body()?.results
            data?.let {
                callback.onResult(it, lastPageResult, lastPageResult++)
            }

        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        isRequestInProgress = true
        runBlocking(Dispatchers.IO) {
            val response = remoteSource.getSearchResults(searchQuery, lastPageResult)
            val data = response.body()?.results
            data?.let {
                callback.onResult(it, lastPageResult++)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        //do nothing
    }

}

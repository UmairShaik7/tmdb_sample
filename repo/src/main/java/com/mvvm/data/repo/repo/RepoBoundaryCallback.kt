package com.mvvm.data.repo.repo

import android.util.Log
import androidx.paging.PagedList
import com.mvvm.data.repo.db.LocalDataSource
import com.mvvm.data.repo.model.Result
import com.mvvm.data.repo.network.RemoteNetworkSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RepoBoundaryCallback(
    private val query: String,
    private val dbSource: LocalDataSource,
    private var remoteSource: RemoteNetworkSource
) : PagedList.BoundaryCallback<Result>() {

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        Log.d("RepoBoundaryCallback", "onZeroItemsLoaded")
        requestAndSaveData(query)
    }

    /**
     * When all items in the database were loaded, we need to query the backend for more items.
     */
    override fun onItemAtEndLoaded(itemAtEnd: Result) {
        Log.d("RepoBoundaryCallback", "onItemAtEndLoaded")
        requestAndSaveData(query)
    }

    private fun requestAndSaveData(query: String) {
        if (isRequestInProgress) return
        GlobalScope.launch(Dispatchers.IO) {


            isRequestInProgress = true
            val data = remoteSource.getGenra(query, lastRequestedPage)
            lastRequestedPage++
            if (data.isSuccessful) {
                dbSource.insertTopMovies(data.body()?.results)
                isRequestInProgress = false
            }
        }
    }
}

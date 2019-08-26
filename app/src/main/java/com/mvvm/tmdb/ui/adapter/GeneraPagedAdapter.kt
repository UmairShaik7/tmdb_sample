package com.mvvm.tmdb.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.mvvm.data.repo.AppConstants
import com.mvvm.data.repo.model.Result
import com.mvvm.tmdb.ui.base.BaseViewModel


class GeneraPagedAdapter(private val viewModel: BaseViewModel) :
    PagedListAdapter<Result, MovieViewGridHolder>(AppConstants.MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewGridHolder {
        return MovieViewGridHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewGridHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            holder.bind(viewModel, repoItem)
        }
    }
}

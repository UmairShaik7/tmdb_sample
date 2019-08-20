package com.mvvm.tmdb.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mvvm.data.repo.AppConstants
import com.mvvm.data.repo.model.Result
import com.mvvm.tmdb.ui.BaseViewModel
import com.mvvm.tmdb.ui.adapter.MovieViewHolder


class GeneraPagedAdapter(private val viewModel: BaseViewModel) :
    PagedListAdapter<Result, MovieViewHolder>(AppConstants.MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            holder.bind(viewModel, repoItem)
        }
    }
}

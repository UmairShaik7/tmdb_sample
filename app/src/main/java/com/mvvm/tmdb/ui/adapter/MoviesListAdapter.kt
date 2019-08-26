package com.mvvm.tmdb.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mvvm.data.repo.AppConstants
import com.mvvm.data.repo.model.Result
import com.mvvm.tmdb.ui.base.BaseViewModel
import com.mvvm.tmdb.ui.home.HomeFragmentViewModel

/**
 * Adapter for the Movies list. Has a reference to the [HomeFragmentViewModel] to send actions back to it.
 */
class MoviesListAdapter(private val viewModel: BaseViewModel) :
    ListAdapter<Result, MovieViewListHolder>(AppConstants.MOVIE_COMPARATOR) {

    override fun onBindViewHolder(holder: MovieViewListHolder, position: Int) {
        getItem(position).let {
            with(holder) {
                itemView.tag = it
                bind(viewModel, it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewListHolder {
        return MovieViewListHolder.from(parent)
    }


}

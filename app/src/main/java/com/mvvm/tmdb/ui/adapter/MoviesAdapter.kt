package com.mvvm.tmdb.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mvvm.data.repo.model.Result
import com.mvvm.tmdb.ui.BaseViewModel
import com.mvvm.tmdb.ui.home.HomeFragmentViewModel

/**
 * Adapter for the Movies list. Has a reference to the [HomeFragmentViewModel] to send actions back to it.
 */
class MoviesAdapter(private val viewModel: BaseViewModel) :
    ListAdapter<Result, MovieViewHolder>(MoviesDiffCallback()) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position).let {
            with(holder) {
                itemView.tag = it
                bind(viewModel, it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }


}

/**
 * Callback for calculating the diff between two non-null latestMoviesResult in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class MoviesDiffCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}

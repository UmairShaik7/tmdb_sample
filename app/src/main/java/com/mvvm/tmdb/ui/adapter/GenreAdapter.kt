package com.mvvm.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.data.repo.AppConstants
import com.mvvm.tmdb.databinding.GenreItemBinding
import com.mvvm.tmdb.ui.home.HomeFragmentViewModel

class GenreAdapter(private val viewModel: HomeFragmentViewModel) :
    ListAdapter<AppConstants.Genre, GenreAdapter.ViewHolder>(GenreDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: GenreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: HomeFragmentViewModel, item: AppConstants.Genre) {

            binding.viewmodel = viewModel
            binding.genre = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GenreItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null latestMoviesResult in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class GenreDiffCallback : DiffUtil.ItemCallback<AppConstants.Genre>() {
    override fun areItemsTheSame(
        oldItem: AppConstants.Genre,
        newItem: AppConstants.Genre
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: AppConstants.Genre,
        newItem: AppConstants.Genre
    ): Boolean {
        return oldItem == newItem
    }
}

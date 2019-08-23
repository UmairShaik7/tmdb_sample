package com.mvvm.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.data.repo.model.Result
import com.mvvm.tmdb.databinding.MovieItemBinding
import com.mvvm.tmdb.ui.base.BaseViewModel

class MovieViewHolder private constructor(private val binding: MovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: BaseViewModel, item: Result) {

        binding.viewmodel = viewModel
        binding.movie = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MovieViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = MovieItemBinding.inflate(layoutInflater, parent, false)

            return MovieViewHolder(binding)
        }
    }
}
package com.mvvm.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.data.repo.model.Result
import com.mvvm.tmdb.databinding.MovieItemListBinding
import com.mvvm.tmdb.ui.base.BaseViewModel

class MovieViewListHolder private constructor(private val binding: MovieItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: BaseViewModel, item: Result) {

        binding.viewmodel = viewModel
        binding.movie = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MovieViewListHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = MovieItemListBinding.inflate(layoutInflater, parent, false)

            return MovieViewListHolder(binding)
        }
    }
}
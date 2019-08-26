package com.mvvm.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.data.repo.model.Result
import com.mvvm.tmdb.databinding.MovieItemGridBinding
import com.mvvm.tmdb.ui.base.BaseViewModel

class MovieViewGridHolder private constructor(private val binding: MovieItemGridBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: BaseViewModel, item: Result) {

        binding.viewmodel = viewModel
        binding.movie = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MovieViewGridHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = MovieItemGridBinding.inflate(layoutInflater, parent, false)

            return MovieViewGridHolder(binding)
        }
    }
}
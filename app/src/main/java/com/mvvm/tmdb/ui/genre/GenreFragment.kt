package com.mvvm.tmdb.ui.genre

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mvvm.tmdb.R

class GenreFragment : Fragment() {

    companion object {
        fun newInstance() = GenreFragment()
    }

    private lateinit var viewModel: GenreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.genre_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GenreViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

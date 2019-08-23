package com.mvvm.tmdb.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.mvvm.tmdb.R
import com.mvvm.tmdb.databinding.MovieDetailsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : Fragment() {

    private lateinit var viewBind: MovieDetailsFragmentBinding
    private val vm: MovieDetailsViewModel by viewModel()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.movie_details_fragment, container, false)

        viewBind = MovieDetailsFragmentBinding.bind(view).apply {
            viewmodel = vm
            lifecycleOwner = viewLifecycleOwner
        }
        return viewBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getMovieWithID.value = args.movieID
        setUpObserver()
    }

    private fun setUpObserver() {
        vm.movieDetails.observe(viewLifecycleOwner, Observer {
            viewBind.movie = it
        })
    }

}

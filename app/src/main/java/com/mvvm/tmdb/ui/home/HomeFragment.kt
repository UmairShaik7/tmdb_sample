package com.mvvm.tmdb.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mvvm.tmdb.EventObserver
import com.mvvm.tmdb.databinding.FragmentHomeBinding
import com.mvvm.tmdb.ui.adapter.GenreAdapter
import com.mvvm.tmdb.ui.adapter.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentHomeBinding
    // lazy inject MyViewModel
    private val vm: HomeFragmentViewModel by viewModel()
    private lateinit var latestMoviesListAdapter: MoviesAdapter
    private lateinit var topMoviesListAdapter: MoviesAdapter
    private lateinit var genreListAdapter: GenreAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //inflater.inflate(R.layout.fragment_home, container, false)
        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false)
        //.inflate(inflater, R.layout.fragment_home, container, false)
        viewDataBinding.viewmodel = vm
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLatestMoviesListAdapter()
        setupTopMoviesListAdapter()
        setupGenreListAdapter()
        setupObservers()
        vm.getMovies()
    }

    private fun setupObservers() {

        vm.genreLiveData.observe(this, EventObserver {
            it.also { getAllGenreCategory(it) }
        })

        vm.latestMoviesResult.observe(viewLifecycleOwner, Observer { it ->
            if (!it.isNullOrEmpty()) latestMoviesListAdapter.submitList(it)
        })
    }

    private fun getAllGenreCategory(genreType: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToGenreFragment(genreType)
        findNavController().navigate(action)
    }

    private fun setupGenreListAdapter() {
        genreListAdapter = GenreAdapter(vm)
        viewDataBinding.genreList.adapter = genreListAdapter
    }

    private fun setupTopMoviesListAdapter() {
        topMoviesListAdapter = MoviesAdapter(vm)
        viewDataBinding.topMoviesList.adapter = topMoviesListAdapter
    }

    private fun setupLatestMoviesListAdapter() {
        latestMoviesListAdapter = MoviesAdapter(vm)
        viewDataBinding.latestMoviesList.adapter = latestMoviesListAdapter
    }


}

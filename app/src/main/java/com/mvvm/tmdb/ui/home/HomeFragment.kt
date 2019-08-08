package com.mvvm.tmdb.ui.home


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mvvm.tmdb.databinding.FragmentHomeBinding
import com.mvvm.tmdb.ui.home.adapter.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentHomeBinding
    // lazy inject MyViewModel
    val vm: HomeFragmentViewModel by viewModel()
    private lateinit var latestMoviesListAdapter: MoviesAdapter
    private lateinit var topMoviesListAdapter: MoviesAdapter

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
        vm.getMovies()
        setUpObserver()
    }

    private fun setUpObserver() {
        vm.topMoviesResult?.observe(this, Observer {
            Log.i(TAG,it.toString())
        })
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

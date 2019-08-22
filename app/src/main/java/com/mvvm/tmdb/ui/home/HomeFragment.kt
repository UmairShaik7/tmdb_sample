package com.mvvm.tmdb.ui.home


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mvvm.tmdb.EventObserver
import com.mvvm.tmdb.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_activity_actions, menu)
        searchView = searchView(menu)
        searchView?.queryHint = getString(R.string.search)
        searchView?.setOnQueryTextListener(onQueryTextListener)
    }

    private var searchView: SearchView? = null

    private var onQueryTextListener: SearchView.OnQueryTextListener? =
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val action = HomeFragmentDirections.actionHomeFragmentToSearchableFragment(query)
                findNavController().navigate(action)
                Toast.makeText(context, query, Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // do nothing
                return true
            }
        }

    private fun searchView(menu: Menu?): SearchView? {
        val searchItem = menu?.findItem(R.id.action_search)
        return searchItem?.actionView as? SearchView
    }


}

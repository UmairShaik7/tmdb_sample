package com.mvvm.tmdb.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mvvm.tmdb.EventObserver
import com.mvvm.tmdb.R
import com.mvvm.tmdb.databinding.SearchableFragmentBinding
import com.mvvm.tmdb.ui.adapter.MoviesListAdapter
import com.mvvm.tmdb.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchableFragment : BaseFragment() {

    private lateinit var gridAdapter: MoviesListAdapter
    private val vm: SearchViewModel by viewModel()
    private lateinit var binding: SearchableFragmentBinding
    private val args: SearchableFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchableFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = vm
            lifecycleOwner = this.lifecycleOwner
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpObservable()
        vm.searchMovies(args.query)
    }

    private fun setUpAdapter() {
        gridAdapter = MoviesListAdapter(vm)
        binding.searchList.adapter = gridAdapter
    }

    private fun setUpObservable() {
        vm.posts.observe(this, Observer {
            gridAdapter.submitList(it)
        })
        vm.itemClick.observe(viewLifecycleOwner, EventObserver {
            showMovieDetails(it)
        })
    }

    override fun showMovieDetails(movieId: Int) {
        val action =
            SearchableFragmentDirections.actionSearchableFragmentToMovieDetailsFragment(movieId)
        findNavController().navigate(action)
    }


    private var searchView: SearchView? = null

    private var onQueryTextListener: SearchView.OnQueryTextListener? =
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                vm.searchMovies(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // do nothing
                return true
            }
        }

    private fun getSearchView(menu: Menu?): SearchView? {
        val searchItem = menu?.findItem(R.id.action_search).apply { }
        val searchView = searchItem?.actionView as? SearchView
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView?.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
            isQueryRefinementEnabled = true
        }
        return searchView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_activity_actions, menu)
        searchView = getSearchView(menu)
        searchView?.queryHint = getString(R.string.search)
        searchView?.setOnQueryTextListener(onQueryTextListener)
    }


}

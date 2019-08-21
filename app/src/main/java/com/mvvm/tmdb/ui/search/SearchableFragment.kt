package com.mvvm.tmdb.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mvvm.tmdb.databinding.SearchableFragmentBinding
import com.mvvm.tmdb.ui.adapter.GeneraPagedAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchableFragment : Fragment() {

    private lateinit var gridAdapter: GeneraPagedAdapter
    private val vm: SearchViewModel by viewModel()
    private lateinit var binding: SearchableFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SearchableFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = vm
            lifecycleOwner = this@SearchableFragment
        }
        return binding.root
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivitySearchableBinding>(
            this, R.layout.searchable_fragment
        )

       // getSearchIntent(intent)
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpObservable()
    }

    private fun setUpAdapter() {

        gridAdapter = GeneraPagedAdapter(vm)
        binding.searchList.adapter = gridAdapter
    }

    private fun setUpObservable() {

        vm.posts.observe(this, Observer { it ->
            gridAdapter.submitList(it)
        })
    }

    /* override fun onNewIntent(intent: Intent?) {
         setIntent(intent)
         getSearchIntent(intent)
         super.onNewIntent(intent)
     }*/

    /* private fun getSearchIntent(intent: Intent?) {
         // Verify the action and get the query
         if (Intent.ACTION_SEARCH == intent?.action) {
             intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                 doMySearch(query)
                 SearchRecentSuggestions(this,
                     SuggestionProvider.AUTHORITY,
                     SuggestionProvider.MODE
                 )
                     .saveRecentQuery(query, null)
             }
         }
     }*/

    /* private fun doMySearch(query: String) {
         Toast.makeText(this, query, Toast.LENGTH_SHORT).show()
         vm.searchMovies(query)
     }*/

    /*  private var searchView: SearchView? = null

      override fun onCreateOptionsMenu(menu: Menu?): Boolean {
          menuInflater.inflate(R.menu.main_activity_actions, menu)
          searchView = searchView(menu)
          searchView?.queryHint = getString(R.string.search)
          searchView?.setOnQueryTextListener(onQueryTextListener)
          return true
      }

      private fun searchView(menu: Menu?): SearchView? {
          val searchItem = menu?.findItem(R.id.action_search)
          return searchItem?.actionView as? SearchView
      }

      private var onQueryTextListener: SearchView.OnQueryTextListener? = object : SearchView.OnQueryTextListener {
          override fun onQueryTextSubmit(query: String): Boolean {
              vm.searchMovies(query)
              return true
          }

          override fun onQueryTextChange(newText: String): Boolean {
              // do nothing
              return true
          }
      }*/


}

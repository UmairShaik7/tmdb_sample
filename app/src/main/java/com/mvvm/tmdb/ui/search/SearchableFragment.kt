package com.mvvm.tmdb.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.mvvm.tmdb.databinding.SearchableFragmentBinding
import com.mvvm.tmdb.ui.adapter.GeneraPagedAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchableFragment : Fragment() {

    private lateinit var gridAdapter: GeneraPagedAdapter
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
            lifecycleOwner = this@SearchableFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpObservable()
        vm.searchMovies(args.query)
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

}

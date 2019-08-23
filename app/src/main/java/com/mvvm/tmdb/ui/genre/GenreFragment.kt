package com.mvvm.tmdb.ui.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagedList
import com.mvvm.data.repo.model.Result
import com.mvvm.tmdb.EventObserver
import com.mvvm.tmdb.R
import com.mvvm.tmdb.databinding.GenreFragmentBinding
import com.mvvm.tmdb.ui.adapter.GeneraPagedAdapter
import com.mvvm.tmdb.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenreFragment : BaseFragment() {

    /* companion object {
         fun newInstance() = GenreFragment()
     }*/

    private lateinit var gridAdapter: GeneraPagedAdapter
    private lateinit var viewBind: GenreFragmentBinding
    private val vm: GenreViewModel by viewModel()
    private val args: GenreFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.genre_fragment, container, false)

        viewBind = GenreFragmentBinding.bind(view).apply { viewmodel = vm }

        viewBind.lifecycleOwner = this.viewLifecycleOwner
        return viewBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupGridAdapter()
        vm.setGenre(args.genreType)
        setUpObservables()
    }

    private fun setUpObservables() {

        vm.repos.observe(viewLifecycleOwner, Observer<PagedList<Result>> {
            gridAdapter.submitList(it)

        })

        vm.itemClick.observe(viewLifecycleOwner, EventObserver {
            getMovieDetails(it)
        })
    }

    override fun getMovieDetails(movieId: Int) {
        val action = GenreFragmentDirections.actionGenreFragmentToMovieDetailsFragment(movieId)
        findNavController().navigate(action)
    }

    private fun setupGridAdapter() {
        gridAdapter = GeneraPagedAdapter(vm)
        viewBind.tasksList.adapter = gridAdapter
    }
}

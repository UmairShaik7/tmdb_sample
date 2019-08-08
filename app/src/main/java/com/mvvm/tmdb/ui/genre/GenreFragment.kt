package com.mvvm.tmdb.ui.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mvvm.tmdb.R
import com.mvvm.tmdb.databinding.GenreFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenreFragment : Fragment() {

    /* companion object {
         fun newInstance() = GenreFragment()
     }*/

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
        viewBind.title.text = args.genreType
    }


}

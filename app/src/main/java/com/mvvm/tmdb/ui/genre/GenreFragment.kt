package com.mvvm.tmdb.ui.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mvvm.tmdb.databinding.GenreFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenreFragment : Fragment() {

   /* companion object {
        fun newInstance() = GenreFragment()
    }*/

    private lateinit var viewBind: GenreFragmentBinding
    private val vm: GenreViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.genre_fragment, container, false)

        // Inflate the layout for this fragment
        //inflater.inflate(R.layout.fragment_home, container, false)
        viewBind = GenreFragmentBinding.inflate(inflater, container, false)
        //.inflate(inflater, R.layout.fragment_home, container, false)
        viewBind.viewmodel = vm
        viewBind.lifecycleOwner = this.viewLifecycleOwner
        return viewBind.root
    }

   /* override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }*/

}

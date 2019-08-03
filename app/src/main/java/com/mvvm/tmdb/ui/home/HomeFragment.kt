package com.mvvm.tmdb.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mvvm.tmdb.R
import com.mvvm.tmdb.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentHomeBinding
    // lazy inject MyViewModel
    val vm: HomeFragmentViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //inflater.inflate(R.layout.fragment_home, container, false)
        viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewDataBinding.viewmodel = vm
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservable()
        vm.getMovies()
    }

    private fun setUpObservable() {
        vm.item.observe(this, Observer {
            //TODO
        })
    }

}

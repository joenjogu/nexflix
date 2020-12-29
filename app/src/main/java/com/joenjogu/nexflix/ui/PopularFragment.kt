package com.joenjogu.nexflix.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.joenjogu.nexflix.adapters.PopularMovieAdapter
import com.joenjogu.nexflix.viewmodels.PopularMovieViewModel
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.databinding.FragmentPopularBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PopularFragment : Fragment() {
    private lateinit var binding: FragmentPopularBinding
//    private lateinit var viewModel: PopularMovieViewModel

//    private val viewModel: PopularMovieViewModel by lazy {
//        val activity = requireNotNull(this.activity) {
//            "You can only access the viewModel after onActivityCreated()"
//        }
//        ViewModelProvider(this, Injection.provideViewModelFactory(activity.application))
//            .get(PopularMovieViewModel::class.java)
//    }

    private val viewModel: PopularMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false)
        val adapter = PopularMovieAdapter()
        binding.adapter = adapter

//        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory(requireContext()))
//            .get(PopularMovieViewModel::class.java)

        viewModel.movies.observe(this, {
            adapter.submitList(it)
        })

        return binding.root
    }

}

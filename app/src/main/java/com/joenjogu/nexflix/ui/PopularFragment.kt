package com.joenjogu.nexflix.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.adapters.PopularMovieAdapter
import com.joenjogu.nexflix.data.core.Result
import com.joenjogu.nexflix.databinding.FragmentPopularBinding
import com.joenjogu.nexflix.viewmodels.PopularMovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PopularFragment : Fragment() {
    private lateinit var binding: FragmentPopularBinding
    private val viewModel: PopularMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false)

        subscribeUi()
//        viewModel.movies.observe(viewLifecycleOwner) {
//            adapter.submitList(it)
//        }
        return binding.root
    }

    private fun subscribeUi() {
        val adapter = PopularMovieAdapter()
        binding.adapter = adapter

        viewModel.popularMoviesResult.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.LOADING -> {
                    binding.popularLoader.visibility = View.VISIBLE
                }
                Result.Status.ERROR -> {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG).show()
                }
                Result.Status.SUCCESS -> {
                    if (!result.data.isNullOrEmpty()) {
                        binding.popularLoader.visibility = View.GONE
                        adapter.submitList(result.data)
                    } else {
                        binding.popularLoader.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}

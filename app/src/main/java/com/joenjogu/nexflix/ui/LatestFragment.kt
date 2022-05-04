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
import com.joenjogu.nexflix.adapters.LatestMovieAdapter
import com.joenjogu.nexflix.data.core.Result
import com.joenjogu.nexflix.databinding.FragmentLatestBinding
import com.joenjogu.nexflix.viewmodels.LatestMovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LatestFragment : Fragment() {
    private lateinit var binding: FragmentLatestBinding
    private val viewModel: LatestMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_latest, container, false)

        subscribeUi()

        return binding.root
    }

    private fun subscribeUi() {
        val adapter = LatestMovieAdapter()
        binding.adapter = adapter

        viewModel.trendingMovies.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Result.Status.LOADING -> {
                    binding.latestLoader.visibility = View.VISIBLE
                }
                Result.Status.ERROR -> {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG).show()
                }
                Result.Status.SUCCESS -> {
                    if (!result.data.isNullOrEmpty()) {
                        binding.latestLoader.visibility = View.GONE
                        adapter.submitList(result.data)
                    } else {
                        binding.latestLoader.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}

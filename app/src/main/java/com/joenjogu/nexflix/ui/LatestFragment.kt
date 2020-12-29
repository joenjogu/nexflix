package com.joenjogu.nexflix.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.joenjogu.nexflix.LatestMovieAdapter
import com.joenjogu.nexflix.LatestMovieViewModel
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.databinding.FragmentLatestBinding
import org.koin.android.viewmodel.ext.android.viewModel

class LatestFragment : Fragment() {
    private lateinit var binding: FragmentLatestBinding
    private val viewModel: LatestMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_latest, container, false)
        val adapter = LatestMovieAdapter()
        binding.adapter = adapter

        viewModel.trendingMovie.observe(this, {
            adapter.submitList(it)
        })

        return binding.root
    }
}

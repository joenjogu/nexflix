package com.joenjogu.nexflix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.joenjogu.nexflix.databinding.FragmentPopularBinding
import com.joenjogu.nexflix.databinding.PopularMovieListItemBinding

class PopularFragment : Fragment() {
    private lateinit var binding: FragmentPopularBinding
    private lateinit var viewModel: PopularMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false)
        val adapter = PopularMovieAdapter()
        binding.adapter = adapter

        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory())
            .get(PopularMovieViewModel::class.java)

        viewModel.movies.observe(this, {
            adapter.submitList(it)
        })

        return binding.root
    }

}
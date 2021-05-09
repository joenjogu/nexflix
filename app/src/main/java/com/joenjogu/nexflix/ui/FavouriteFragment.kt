package com.joenjogu.nexflix.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.adapters.PopularMovieAdapter
import com.joenjogu.nexflix.databinding.FragmentFavouriteBinding
import com.joenjogu.nexflix.viewmodels.FavouriteMovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment() {
    private lateinit var dataBinding : FragmentFavouriteBinding
    private val favouriteMovieViewModel: FavouriteMovieViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite,container, false )

        val adapter = PopularMovieAdapter(requireContext())
        dataBinding.adapter = adapter

        favouriteMovieViewModel.favouriteMovies.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return dataBinding.root
    }
}
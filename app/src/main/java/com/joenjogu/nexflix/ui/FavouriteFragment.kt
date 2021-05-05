package com.joenjogu.nexflix.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.adapters.PopularMovieAdapter
import com.joenjogu.nexflix.databinding.FragmentPopularBinding
import com.joenjogu.nexflix.viewmodels.FavouriteMovieViewModel

class FavouriteFragment : Fragment() {
    private lateinit var dataBinding : FragmentPopularBinding
    private val favouriteMovieViewModel: FavouriteMovieViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular,container, false )

        val adapter = PopularMovieAdapter(requireContext())

        favouriteMovieViewModel.favouriteMovies.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return dataBinding.root
    }
}
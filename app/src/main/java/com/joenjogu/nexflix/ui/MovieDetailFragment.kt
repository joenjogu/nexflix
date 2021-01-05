package com.joenjogu.nexflix.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.databinding.FragmentMovieDetailBinding
import com.joenjogu.nexflix.databinding.MovieDetailRecyclerviewBinding

class MovieDetailFragment : Fragment() {
    private lateinit var detailBinding: FragmentMovieDetailBinding
    private lateinit var movieBinding: MovieDetailRecyclerviewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        detailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)

//        impl movieBinding adapter

        return detailBinding.root
    }
}
package com.joenjogu.nexflix.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.adapters.MovieDetailAdapter
import com.joenjogu.nexflix.adapters.PopularMovieAdapter
import com.joenjogu.nexflix.databinding.FragmentMovieDetailBinding
import com.joenjogu.nexflix.databinding.MovieDetailRecyclerviewBinding
import com.joenjogu.nexflix.databinding.PopularMovieListItemBinding
import com.joenjogu.nexflix.models.Movie
import com.joenjogu.nexflix.models.RecommendedMovie
import com.joenjogu.nexflix.utils.toPopularMovie
import com.joenjogu.nexflix.viewmodels.MovieDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {
    private lateinit var detailBinding: FragmentMovieDetailBinding
    private val movieDetailViewModel: MovieDetailViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        detailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        val adapter = PopularMovieAdapter()
        detailBinding.recyclerviewLayout.movieDetailRecyclerview.adapter = adapter

        movieDetailViewModel.movie.observe(viewLifecycleOwner, {
            detailBinding.movie = it
        })

        movieDetailViewModel.recommendedMovies.observe(viewLifecycleOwner, { recommendedMovie ->
            val movieList = mutableListOf<Movie>()
            for (movie in recommendedMovie) {
                val converted = movie.toPopularMovie()
                movieList.add(converted)
            }
            adapter.submitList(movieList)
        })

        return detailBinding.root
    }
}
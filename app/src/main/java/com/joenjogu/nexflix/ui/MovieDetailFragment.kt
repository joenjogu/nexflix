package com.joenjogu.nexflix.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
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
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
//        movieDetailViewModel.id = args.movieId.toString()
        // Inflate the layout for this fragment
        detailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        val adapter = PopularMovieAdapter()
        detailBinding.recyclerviewLayout.movieDetailRecyclerview.adapter = adapter
//        val ad = detailBinding.recyclerviewLayout.movieDetailRecyclerview.adapter

        movieDetailViewModel.movie.observe(viewLifecycleOwner) {
            detailBinding.movie = it
            Log.d("Movie", "onCreateView: {${it.imageUrl}, ${it.id}}")
        }

        movieDetailViewModel.recommendedMovies.observe(viewLifecycleOwner) { recommendedMovie ->
            val movieList = mutableListOf<Movie>()
            for (movie in recommendedMovie) {
                val converted = movie.toPopularMovie()
                movieList.add(converted)
            }
            adapter.submitList(movieList)
        }

        return detailBinding.root
    }
}
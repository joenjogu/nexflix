package com.joenjogu.nexflix.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.adapters.PopularMovieAdapter
import com.joenjogu.nexflix.databinding.FragmentMovieDetailBinding
import com.joenjogu.nexflix.viewmodels.MovieDetailViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MovieDetailFragment : Fragment() {
    private lateinit var detailBinding: FragmentMovieDetailBinding

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val movieDetailViewModel: MovieDetailViewModel by inject { parametersOf(args.movieId.toString())}

        // Inflate the layout for this fragment
        detailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        detailBinding.lifecycleOwner = this
        val adapter = PopularMovieAdapter(context)
        detailBinding.recyclerviewLayout.movieDetailRecyclerview.adapter = adapter

        movieDetailViewModel.movie.observe(viewLifecycleOwner) {
            detailBinding.movie = it
            Log.d("Movie", "onCreateView: {${it.imageUrl}, ${it.id}}")
        }

        movieDetailViewModel.recommendedMovies.observe(viewLifecycleOwner) { recommendedMovie ->
            Log.d("Movie", "recommendedFromDB: $recommendedMovie")
            adapter.submitList(recommendedMovie)
        }

        val direction = MovieDetailFragmentDirections.actionMovieDetailFragmentToViewPagerFragment()
        detailBinding.toolbar.setNavigationOnClickListener {
            it.findNavController().navigate(direction)
        }
        detailBinding.toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }

        return detailBinding.root
    }
}
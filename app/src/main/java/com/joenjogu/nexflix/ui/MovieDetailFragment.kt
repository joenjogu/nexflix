package com.joenjogu.nexflix.ui

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bosphere.fadingedgelayout.FadingEdgeLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.adapters.PopularMovieAdapter
import com.joenjogu.nexflix.data.core.Result
import com.joenjogu.nexflix.databinding.FragmentMovieDetailBinding
import com.joenjogu.nexflix.utils.GlideUtil
import com.joenjogu.nexflix.viewmodels.MovieDetailViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MovieDetailFragment : Fragment() {
    private lateinit var detailBinding: FragmentMovieDetailBinding

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val movieDetailViewModel: MovieDetailViewModel by inject { parametersOf(args.movieId.toString()) }

        // Inflate the layout for this fragment
        detailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        detailBinding.lifecycleOwner = this
        val adapter = PopularMovieAdapter()
        detailBinding.recyclerviewLayout.movieDetailRecyclerview.adapter = adapter

        movieDetailViewModel.movieResult.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.LOADING -> {

                }
                Result.Status.ERROR -> {

                }
                Result.Status.SUCCESS -> {
                    detailBinding.movie = result.data

                    setupFadingLayout(
                        requireActivity(),
                        result.data!!.backdropUrl,
                        detailBinding.toolbarMoviePoster,
                        detailBinding.fadingEdgeLayout,
                        detailBinding.collapsingToolbarLayout
                    )

                }
            }
        })

        movieDetailViewModel.recommendedMoviesResult.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.LOADING -> {
                    detailBinding.movieDetailProgress.visibility = View.VISIBLE
                }
                Result.Status.ERROR -> {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG).show()
                }
                Result.Status.SUCCESS -> {
                    if (!result.data.isNullOrEmpty()) {
                        detailBinding.movieDetailProgress.visibility = View.GONE
                        adapter.submitList(result.data)
                    } else {
                        detailBinding.movieDetailProgress.visibility = View.VISIBLE
                    }
                }
            }
        })

        detailBinding.toolbarFab.setOnClickListener { fab ->
            movieDetailViewModel.setFavourite()
            fab.setBackgroundColor(0xFF00FF)
            Toast.makeText(requireContext(), "Added to Favourites", Toast.LENGTH_LONG).show()
        }

        setupNavigation()

        return detailBinding.root
    }

    private fun setupNavigation() {
        val direction = MovieDetailFragmentDirections.actionMovieDetailFragmentToViewPagerFragment()
        detailBinding.toolbar.setNavigationOnClickListener {
            it.findNavController().navigate(direction)
        }
        detailBinding.toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
    }

    private fun setupFadingLayout(
        fragmentActivity: FragmentActivity,
        imageUrl: String,
        imageView: ImageView,
        fadingEdgeLayout: FadingEdgeLayout,
        collapsingToolbarLayout: CollapsingToolbarLayout
    ) {
        GlideUtil.getScrimPalette(
            fragmentActivity,
            imageUrl,
            imageView,
            fadingEdgeLayout,
            collapsingToolbarLayout
        )
    }

    override fun onPause() {
        super.onPause()
        var typedValue: TypedValue = TypedValue()
        requireContext().theme.resolveAttribute(R.attr.colorOnPrimary, typedValue, true)
        @ColorInt
        requireActivity().window.statusBarColor = typedValue.data
    }
}

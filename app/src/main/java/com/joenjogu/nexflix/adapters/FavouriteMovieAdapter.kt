package com.joenjogu.nexflix.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.databinding.PopularMovieListItemBinding
import com.joenjogu.nexflix.models.Movie
import com.joenjogu.nexflix.ui.FavouriteFragmentDirections
import com.joenjogu.nexflix.ui.SearchResultFragmentDirections
import com.joenjogu.nexflix.utils.Comparison

class FavouriteMovieAdapter : ListAdapter<Movie, FavouriteMovieAdapter.FavouriteMovieViewHolder>(Comparison) {
    class FavouriteMovieViewHolder(val binding: PopularMovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, clickListener: View.OnClickListener) {
            binding.movie = movie
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PopularMovieListItemBinding.inflate(inflater, parent, false)

        return FavouriteMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteMovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, createOnClickListener(movie.id))
    }

    private fun createOnClickListener(movieId: Int): View.OnClickListener {
        return View.OnClickListener {
            when (it.findNavController().currentDestination?.id){
                R.id.favouriteFragment -> {
                    val direction =
                        FavouriteFragmentDirections.
                        actionFavouriteFragmentToMovieDetailFragment(movieId)
                    it.findNavController().navigate(direction)
                }
                R.id.searchResultFragment -> {
                    val direction =
                        SearchResultFragmentDirections.
                        actionSearchResultFragmentToMovieDetailFragment(movieId)
                    it.findNavController().navigate(direction)
                }
            }

        }
    }
}

package com.joenjogu.nexflix.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joenjogu.nexflix.databinding.PopularMovieListItemBinding
import com.joenjogu.nexflix.models.Movie
import com.joenjogu.nexflix.utils.Comparison

class FavouriteMovieAdapter : ListAdapter<Movie, FavouriteMovieAdapter.FavouriteMovieViewHolder>(Comparison) {
    class FavouriteMovieViewHolder(val binding: PopularMovieListItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(movie: Movie) {
                binding.movie = movie
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
        holder.bind(movie)
    }

}
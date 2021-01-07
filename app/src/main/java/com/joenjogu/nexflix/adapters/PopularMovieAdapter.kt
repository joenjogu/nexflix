package com.joenjogu.nexflix.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joenjogu.nexflix.models.Movie
import com.joenjogu.nexflix.databinding.PopularMovieListItemBinding

class PopularMovieAdapter : ListAdapter<Movie, PopularMovieAdapter.PopularMovieViewHolder>(Comparison) {

    class PopularMovieViewHolder(val binding: PopularMovieListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: Movie, listener: View.OnClickListener) {
                binding.movie = item
                binding.clickListener = listener
                binding.executePendingBindings()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PopularMovieListItemBinding.inflate(inflater, parent, false)

        return PopularMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, createOnClickListener(movie.id))
    }

    private fun createOnClickListener(movieId: Int): View.OnClickListener {
        return View.OnClickListener {
            // implement navigation direction with safeargs
        }
    }

    companion object Comparison : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

    }
}
package com.joenjogu.nexflix

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joenjogu.nexflix.databinding.LatestMovieListItemBinding

class LatestMovieAdapter : ListAdapter<Movie, LatestMovieAdapter.LatestMovieViewHolder>(Comparison) {

    class LatestMovieViewHolder(val binding: LatestMovieListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LatestMovieListItemBinding.inflate(inflater, parent, false)

        return LatestMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LatestMovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.binding.movie = movie
        holder.binding.executePendingBindings()
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
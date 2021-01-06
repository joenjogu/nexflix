package com.joenjogu.nexflix.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joenjogu.nexflix.databinding.MovieDetailRecyclerviewBinding
import com.joenjogu.nexflix.models.Movie

class MovieDetailAdapter : ListAdapter<Movie, MovieDetailAdapter.MovieDetailViewHolder>(DiffUtilCallback) {

    class MovieDetailViewHolder(val binding: MovieDetailRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieDetailRecyclerviewBinding.inflate(inflater, parent, false)

        return MovieDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieDetailViewHolder, position: Int) {
        val movie = getItem(position)

        // fix movie detail list item
        holder.binding.movieDetailRecyclerview
        holder.binding.executePendingBindings()
    }

    companion object DiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

    }
}
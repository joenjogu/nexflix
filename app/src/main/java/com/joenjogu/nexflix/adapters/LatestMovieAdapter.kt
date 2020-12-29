package com.joenjogu.nexflix.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joenjogu.nexflix.models.TrendingMovie
import com.joenjogu.nexflix.databinding.LatestMovieListItemBinding

class LatestMovieAdapter : ListAdapter<TrendingMovie, LatestMovieAdapter.LatestMovieViewHolder>(Comparison) {

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

    companion object Comparison : DiffUtil.ItemCallback<TrendingMovie>() {
        override fun areItemsTheSame(oldItem: TrendingMovie, newItem: TrendingMovie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TrendingMovie, newItem: TrendingMovie): Boolean {
            return oldItem.id == newItem.id
        }

    }
}
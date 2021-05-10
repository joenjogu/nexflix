package com.joenjogu.nexflix.utils

import androidx.recyclerview.widget.DiffUtil
import com.joenjogu.nexflix.models.Movie

object Comparison : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
}
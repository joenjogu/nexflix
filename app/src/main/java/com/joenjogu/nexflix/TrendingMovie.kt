package com.joenjogu.nexflix

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrendingMovie(
        @PrimaryKey
        val id: Int,
        val imageUrl: String,
        val title: String,
        val overview: String,
        val rating: Double,
        val released: String
)

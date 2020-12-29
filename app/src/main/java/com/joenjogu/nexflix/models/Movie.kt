package com.joenjogu.nexflix.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    val id: Int,
    val imageUrl: String,
    val title: String,
    val overview: String,
    val rating: Double,
    val released: String
)

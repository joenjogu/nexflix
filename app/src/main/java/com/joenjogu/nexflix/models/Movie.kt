package com.joenjogu.nexflix.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joenjogu.nexflix.utils.Category

@Entity
data class Movie(
    @PrimaryKey
    val id: Int,
    val imageUrl: String,
    val title: String,
    val overview: String,
    val rating: Double,
    val released: String,
    val category: Category = Category.TopRated
)

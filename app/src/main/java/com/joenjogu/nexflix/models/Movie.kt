package com.joenjogu.nexflix.models

import androidx.room.Entity
import androidx.room.Index
import com.joenjogu.nexflix.utils.Category

@Entity(indices = [Index("id"), Index("category")], primaryKeys = ["id", "category"])
data class Movie(
    val id: Int,
    val posterUrl: String,
    val backdropUrl: String,
    val title: String,
    val overview: String,
    val rating: Double,
    val released: String,
    // insert arrayList for multiple movies with multiple recommendations
    val category: Category,
    val recommendedId: Int = 0,
    val favourite: Boolean = false
)

package com.joenjogu.nexflix.models

import androidx.room.Entity
import androidx.room.Index
import com.joenjogu.nexflix.utils.Category

@Entity(indices = [Index("id"), Index("category")], primaryKeys = ["id", "category"])
data class Movie(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val overview: String,
    val rating: Double,
    val released: String,
    val category: Category,
    // insert arrayList for multiple movies with multiple recommendations
    val recommendedId: Int = 0,
    val favourite: Boolean = false
)

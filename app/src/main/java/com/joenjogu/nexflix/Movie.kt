package com.joenjogu.nexflix

data class Movie(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val overview: String,
    val rating: Double,
    val released: String
)

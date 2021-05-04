package com.joenjogu.nexflix.models

import androidx.room.Entity
import com.joenjogu.nexflix.utils.Category

@Entity
data class MovieFavouriteUpdate(
    val id: Int,
    val category: Category,
    val favourite: Boolean
)
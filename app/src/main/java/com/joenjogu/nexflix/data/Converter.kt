package com.joenjogu.nexflix.data

import androidx.room.TypeConverter
import com.joenjogu.nexflix.utils.Category

class Converter {
    @TypeConverter
    fun categoryToInt(category: Category): Int {
        return category.ordinal
    }

    @TypeConverter
    fun intToCategory(int: Int): Category {
        return when(int) {
            0 -> Category.TopRated
            1 -> Category.Trending
            2 -> Category.Recommended
            else -> Category.TopRated
        }
    }
}
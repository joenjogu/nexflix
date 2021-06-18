package com.joenjogu.nexflix.models

import com.joenjogu.nexflix.utils.Category
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieTest {
    private lateinit var movie: Movie

    @Test
    fun test_default_value() {
        val movie = Movie(1, "https:// url","https:// url", "Tenet", "time", 2.4, "2020", Category.TopRated)

        assertEquals(0, movie.recommendedId)
    }
}

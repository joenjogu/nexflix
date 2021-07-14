package com.joenjogu.nexflix.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class DataConvertersTest() {

    @Test
    fun `given a rating double convert to string`() {

        val ratingString = convertDoubleRatingToString(5.6)

        assertEquals(ratingString, "5.6")
    }

    @Test
    fun `given a date string truncate to the year`() {
        val year = reduceDateStringToYear("2021-4-5")

        assertEquals(year, "2021")
    }
}
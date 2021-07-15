package com.joenjogu.nexflix.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DataConvertersTest() {

    @Test
    fun `given a rating double convert to string`() {
        val ratingString = convertDoubleRatingToString(5.6893)
        assertEquals(ratingString, "5.7")
    }

    @Test
    fun `given a date string truncate to the year`() {
        val year = reduceDateStringToYear("2021-4-5")
        assertEquals(year, "2021")
    }

    @Test
    fun `given a date string convert to datettime and return the year`() {
        val year = convertDateToYear("2021-4-5")
        assertEquals(year, "Apr, 2021")
    }
}
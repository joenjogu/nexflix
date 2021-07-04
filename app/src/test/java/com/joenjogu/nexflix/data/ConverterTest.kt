package com.joenjogu.nexflix.data

import com.joenjogu.nexflix.utils.Category
import org.junit.Assert.assertEquals
import org.junit.Test

class ConverterTest {

    val category = Category.TopRated

    @Test
    fun category_to_int_test() {
        assertEquals(category.ordinal, 0)
    }

    @Test
    fun int_to_category_test() {
        assertEquals(0, Category.TopRated.ordinal)
    }
}
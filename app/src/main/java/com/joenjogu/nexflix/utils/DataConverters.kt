package com.joenjogu.nexflix.utils

import java.math.RoundingMode
import java.text.SimpleDateFormat

fun convertDoubleRatingToString(ratingDouble: Double): String {
    return ratingDouble.toBigDecimal().setScale(1, RoundingMode.HALF_EVEN).toString()
}

fun reduceDateStringToYear(date: String): String {
    return date.substring(0, 4)
}

fun convertDateToYear(date: String): String {
    val pattern = "yyyy-MM-dd"
    val newPattern = "MMM, yyyy"
    val originalFormat = SimpleDateFormat(pattern).parse(date)
    val targetFormat = SimpleDateFormat(newPattern)
    return targetFormat.format(originalFormat!!)
}
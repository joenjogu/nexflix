package com.joenjogu.nexflix.utils

fun convertDoubleRatingToString(ratingDouble: Double): String {

    return ratingDouble.toBigDecimal().toPlainString()
}

fun reduceDateStringToYear(date: String): String {
    return date.substring(0, 4)
}
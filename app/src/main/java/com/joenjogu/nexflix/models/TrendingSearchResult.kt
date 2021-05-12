package com.joenjogu.nexflix.models

sealed class TrendingSearchResult {
    data class Success(val data: List<Movie>) : TrendingSearchResult()
    data class Error(val exception: Exception) : TrendingSearchResult()
}

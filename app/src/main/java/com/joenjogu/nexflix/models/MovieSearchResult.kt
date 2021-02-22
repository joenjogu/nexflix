package com.joenjogu.nexflix.models

sealed class MovieSearchResult {
    data class Success(val data: List<Movie>) : MovieSearchResult()
    data class Error(val exception: Exception) : MovieSearchResult()
}
package com.joenjogu.nexflix

fun SearchResult.toDomain(): Movie {
    val urlPrefix = "https://image.tmdb.org/t/p/w500/"
    return Movie(
        this.id,
        urlPrefix + this.poster_path,
        this.title,
        this.overview,
        this.vote_average,
        this.release_date
    )
}
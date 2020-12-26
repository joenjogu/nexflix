package com.joenjogu.nexflix

const val urlPrefix = "https://image.tmdb.org/t/p/w500/"
fun SearchResult.toDomain(): Movie {
    return Movie(
        this.id,
        urlPrefix + this.poster_path,
        this.title,
        this.overview,
        this.vote_average,
        this.release_date
    )
}

fun TrendingResult.toDomain(): Movie {
    return Movie(
            this.id,
            urlPrefix + this.poster_path,
            this.title,
            this.overview,
            this.vote_average,
            this.release_date
    )
}
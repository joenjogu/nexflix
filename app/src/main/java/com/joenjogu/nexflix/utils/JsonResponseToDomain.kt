package com.joenjogu.nexflix.utils

import com.joenjogu.nexflix.RecommendedResult
import com.joenjogu.nexflix.models.*

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

fun TrendingResult.toDomain(): TrendingMovie {
    return TrendingMovie(
            this.id,
            urlPrefix + this.poster_path,
            this.title,
            this.overview,
            this.vote_average,
            this.release_date
    )
}

fun RecommendedResult.toDomain(): RecommendedMovie {
    return RecommendedMovie(
        this.id,
        urlPrefix + this.poster_path,
        this.title,
        this.overview,
        this.vote_average,
        this.release_date
    )
}

fun RecommendedMovie.toPopularMovie(): Movie {
    return Movie(
            this.id,
            this.imageUrl,
            this.title,
            this.overview,
            this.rating,
            this.released
    )
}

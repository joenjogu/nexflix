package com.joenjogu.nexflix.utils

import com.joenjogu.nexflix.models.RecommendedResult
import com.joenjogu.nexflix.models.*

const val urlPrefix = "https://image.tmdb.org/t/p/w500/"
fun MovieResult.toDomain(): Movie {
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

fun RecommendedResult.toDomain(): Movie {
    return Movie(
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

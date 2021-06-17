package com.joenjogu.nexflix.utils

import com.joenjogu.nexflix.models.Movie
import com.joenjogu.nexflix.models.MovieResult
import com.joenjogu.nexflix.models.TrendingResult

const val urlPrefix = "https://image.tmdb.org/t/p/w500/"
fun MovieResult.toPopularDomain(): Movie {
    return Movie(
        this.id,
        urlPrefix + this.poster_path,
        urlPrefix + this.backdrop_path,
        this.title,
        this.overview,
        this.vote_average,
        this.release_date,
        Category.TopRated
    )
}

fun MovieResult.toRecommendedDomain(movieId: Int): Movie {
    return Movie(
        this.id,
        urlPrefix + this.poster_path,
        urlPrefix + this.backdrop_path,
        this.title,
        this.overview,
        this.vote_average,
        this.release_date,
        Category.Recommended,
        recommendedId = movieId
    )
}

fun TrendingResult.toTrendingDomain(): Movie {
    return Movie(
        this.id,
        urlPrefix + this.poster_path,
        urlPrefix + this.backdrop_path,
        this.title,
        this.overview,
        this.vote_average,
        this.release_date,
        Category.Trending
    )
}

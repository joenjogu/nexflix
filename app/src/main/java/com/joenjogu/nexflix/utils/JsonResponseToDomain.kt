package com.joenjogu.nexflix

import com.joenjogu.nexflix.models.Movie
import com.joenjogu.nexflix.models.SearchResult
import com.joenjogu.nexflix.models.TrendingMovie
import com.joenjogu.nexflix.models.TrendingResult

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

fun MovieResponse.toDomain(): Movie {
    return Movie(
        this.id,
        urlPrefix + this.poster_path,
        this.title,
        this.overview,
        this.vote_average,
        this.release_date
    )
}

package com.joenjogu.nexflix.utils

import com.joenjogu.nexflix.models.MovieResult
import com.joenjogu.nexflix.models.TrendingResult
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class JsonResponseToDomainTest {

    private var movieResult: MovieResult? = null

    @Before
    fun setUpMovie() {
        movieResult =
            MovieResult(
                adult = false,
                backdrop_path = "https://backdrop.com",
                genre_ids = listOf(1,2,3,4),
                id = 1,
                original_language = "en",
                original_title = "TENET",
                overview = "Time...backwards",
                popularity = 5.2,
                poster_path = "https://poster.com",
                release_date = "2020",
                title = "TENET",
                video = true,
                vote_average = 5.2,
                vote_count = 1
            )
    }

    @Test
    fun test_movie_to_popular_domain() {
        val movie = movieResult?.toPopularDomain()
        assertEquals(movie?.category, Category.TopRated)
    }

    @Test
    fun test_movieresult_to_recommended_domain() {
        val movie = movieResult?.toRecommendedDomain(1)
        assertEquals(movie?.category, Category.Recommended)
    }

    @Test
    fun test_movieresult_to_trending_domain() {
        val trendingResult = movieResult?.let {
            TrendingResult(
                adult = it.adult,
                backdrop_path = it.backdrop_path,
                genre_ids = it.genre_ids,
                id = it.id,
                media_type = "Movie",
                original_language = it.original_language,
                original_title = it.original_title,
                overview = it.overview,
                popularity = it.popularity,
                poster_path = it.poster_path,
                release_date = it.release_date,
                title = it.title,
                video = it.video,
                vote_average = it.vote_average,
                vote_count = it.vote_count
            )
        }
        val movie = trendingResult?.toTrendingDomain()
        assertEquals(movie?.category, Category.Trending)
    }

}
package com.joenjogu.nexflix.data

import com.joenjogu.nexflix.models.RecommendationResponse
import com.joenjogu.nexflix.models.SearchResponse
import com.joenjogu.nexflix.models.SearchResult
import com.joenjogu.nexflix.models.TrendingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): SearchResult

    @GET("movie/popular")
    suspend fun getPopularMovies(): SearchResponse

    @GET("movie/top_rated")
    suspend fun getLatestMovies(@Query("api_key") apiKey: String): SearchResponse

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(@Query("api_key") apiKey: String): TrendingResponse

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendedMovies(
            @Path("movie_id") movieId: Int,
            @Query("api_key") apiKey: String
    ): RecommendationResponse

}
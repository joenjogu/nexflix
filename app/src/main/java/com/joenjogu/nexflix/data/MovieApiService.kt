package com.joenjogu.nexflix.data

import com.joenjogu.nexflix.BuildConfig
import com.joenjogu.nexflix.models.MovieResult
import com.joenjogu.nexflix.models.SearchResponse
import com.joenjogu.nexflix.models.TrendingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<MovieResult>

    @GET("movie/top_rated")
    suspend fun getTopMovies(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<SearchResponse>

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<TrendingResponse>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendedMovies(
            @Path("movie_id") movieId: Int,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<SearchResponse>

}
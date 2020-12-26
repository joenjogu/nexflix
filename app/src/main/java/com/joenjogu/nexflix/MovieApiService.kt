package com.joenjogu.nexflix

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/")
    suspend fun getMovie(@Query("id") params: Int): SearchResult

    @GET("movie/popular")
    suspend fun getPopularMovies(): SearchResponse

    @GET("movie/top_rated")
    suspend fun getLatestMovies(@Query("api_key") apiKey: String): SearchResponse

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(@Query("api_key") apiKey: String): TrendingResponse

}
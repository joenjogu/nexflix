package com.joenjogu.nexflix

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/")
    suspend fun getMovie(@Query("id") params: Int): Result

    @GET("movie/popular")
    suspend fun getPopularMovies(): SearchResponse

    @GET("movie/top_rated")
    suspend fun getLatestMovies(@Query("api_key") apiKey: String): SearchResponse

    companion object {
        fun createService(): MoviesApiService {
            val BASE_URL = "https://api.themoviedb.org/3/"

            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
//                .addInterceptor(TokenInterceptor())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(MoviesApiService::class.java)
        }
    }
}
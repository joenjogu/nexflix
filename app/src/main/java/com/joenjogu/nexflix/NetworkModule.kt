package com.joenjogu.nexflix

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideMovieApiService(retrofit = get ()) }

    single { provideRetrofit(client = get(), url = "https://api.themoviedb.org/3/") }

    single { provideOkHttpClient() }

}

private fun provideOkHttpClient(): OkHttpClient{
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

private fun provideRetrofit(client: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

private fun provideMovieApiService(retrofit: Retrofit): MoviesApiService {
    return retrofit.create(MoviesApiService::class.java)
}
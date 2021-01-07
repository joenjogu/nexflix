package com.joenjogu.nexflix.di

import androidx.room.Room
import com.joenjogu.nexflix.data.MovieDao
import com.joenjogu.nexflix.data.MovieDatabase
import com.joenjogu.nexflix.data.RecommendedMovieDao
import com.joenjogu.nexflix.data.TrendingMovieDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single { provideMovieDao(database = get()) }

    single { provideTrendingMovieDao(database = get()) }

    single { provideRecommendedMovieDao(database = get()) }

    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "movie_db").build()
    }
}

fun provideRecommendedMovieDao(database: MovieDatabase): RecommendedMovieDao {
    return database.recommendedMovieDao
}

private fun provideMovieDao(database: MovieDatabase): MovieDao {
    return database.movieDao
}

private fun provideTrendingMovieDao(database: MovieDatabase): TrendingMovieDao {
    return database.trendingMovieDao
}

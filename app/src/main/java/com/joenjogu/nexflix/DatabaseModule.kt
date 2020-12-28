package com.joenjogu.nexflix

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single { provideMovieDao(database = get()) }

    single { provideTrendingMovieDao(database = get()) }

    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "movie_db").build()
    }
}

private fun provideMovieDao(database: MovieDatabase): MovieDao {
    return database.movieDao
}

private fun provideTrendingMovieDao(database: MovieDatabase): TrendingMovieDao {
    return database.trendingMovieDao
}

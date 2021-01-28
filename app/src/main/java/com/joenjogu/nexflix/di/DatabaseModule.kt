package com.joenjogu.nexflix.di

import androidx.room.Room
import com.joenjogu.nexflix.data.MovieDao
import com.joenjogu.nexflix.data.MovieDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single { provideMovieDao(database = get()) }

    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "movie_db").fallbackToDestructiveMigration().build()
    }
}

private fun provideMovieDao(database: MovieDatabase): MovieDao {
    return database.movieDao
}
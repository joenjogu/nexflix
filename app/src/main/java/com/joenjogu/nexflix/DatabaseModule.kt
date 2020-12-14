package com.joenjogu.nexflix

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single { provideDao(database = get()) }

    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "movie_db").build()
    }
}

private fun provideDao(database: MovieDatabase): MovieDao {
    return database.movieDao
}

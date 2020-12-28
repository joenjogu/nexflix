package com.joenjogu.nexflix

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Movie::class, TrendingMovie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao
    abstract val trendingMovieDao: TrendingMovieDao

}
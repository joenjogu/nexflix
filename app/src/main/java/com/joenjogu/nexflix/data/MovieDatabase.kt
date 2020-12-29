package com.joenjogu.nexflix.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joenjogu.nexflix.models.Movie
import com.joenjogu.nexflix.models.TrendingMovie

@Database(entities = [Movie::class, TrendingMovie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao
    abstract val trendingMovieDao: TrendingMovieDao

}
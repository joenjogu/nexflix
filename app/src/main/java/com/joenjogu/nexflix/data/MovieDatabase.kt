package com.joenjogu.nexflix.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joenjogu.nexflix.models.Movie
import com.joenjogu.nexflix.models.RecommendedMovie
import com.joenjogu.nexflix.models.TrendingMovie

@Database(entities = [Movie::class, TrendingMovie::class, RecommendedMovie::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao
    abstract val trendingMovieDao: TrendingMovieDao
    abstract val recommendedMovieDao: RecommendedMovieDao

}
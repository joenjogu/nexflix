package com.joenjogu.nexflix.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joenjogu.nexflix.models.TrendingMovie

@Dao
interface TrendingMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<TrendingMovie>)

    @Query("SELECT * FROM trendingmovie")
    fun getAllMovies() : LiveData<List<TrendingMovie>>
}
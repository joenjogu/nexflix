package com.joenjogu.nexflix

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TrendingMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<TrendingMovie>)

    @Query("SELECT * FROM movie")
    fun getAllMovies() : LiveData<List<TrendingMovie>>
}
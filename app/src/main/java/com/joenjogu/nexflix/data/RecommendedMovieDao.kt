package com.joenjogu.nexflix.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joenjogu.nexflix.models.RecommendedMovie

@Dao
interface RecommendedMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendedMovies(movies: List<RecommendedMovie>)

    @Query("SELECT * FROM recommendedmovie")
    fun getAllMovies(): LiveData<List<RecommendedMovie>>
}
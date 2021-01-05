package com.joenjogu.nexflix.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joenjogu.nexflix.models.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<Movie>)

    @Query("SELECT * FROM movie")
    fun getAllMovies() : LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieById(id: Int): LiveData<Movie>
}
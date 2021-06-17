package com.joenjogu.nexflix.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.joenjogu.nexflix.models.Movie
import com.joenjogu.nexflix.models.MovieFavouriteUpdate
import com.joenjogu.nexflix.utils.Category

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<Movie>)

    @Update(entity = Movie::class)
    suspend fun updateFavourite(fav: MovieFavouriteUpdate)

    @Query("SELECT * FROM movie WHERE category = :category")
    fun getAllMovies(category: Category): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieById(id: Int): LiveData<Movie>

    @Query("SELECT * FROM movie WHERE recommendedId = :movieId")
    fun getRecommendedMovies(movieId: Int): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE favourite = 1")
    fun getFavouriteMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE title LIKE :movieTitle")
    fun searchMovies(movieTitle: String): LiveData<List<Movie>>
}

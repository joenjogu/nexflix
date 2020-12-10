package com.joenjogu.nexflix

import android.util.Log
import androidx.lifecycle.LiveData

class MovieRepository(private val apiService: MoviesApiService, private val movieDatabase: MovieDatabase) {

    val movies: LiveData<List<Movie>> = movieDatabase.movieDao.getAllMovies()

    suspend fun getMovie(id: Int) {
        val response = apiService.getMovie(id)
    }

    suspend fun getPopularMovies(): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        try {
            val response = apiService.getLatestMovies("2d9aa26f9b71ca6d8a3db85d730e19a4")
            val results = response.searchResults
            for (result in results) {
                movies.add(result.toDomain())
            }
            Log.d("Repo", "getPopularMovies: $movies")
            movieDatabase.movieDao.insertAllMovies(movies)
            return movies
        } catch (exception: Throwable){
            Log.e("Repo", "getPopularMovies: ", exception)
        }
        return movies
    }

    suspend fun getTopRatedMovies() {

    }
}
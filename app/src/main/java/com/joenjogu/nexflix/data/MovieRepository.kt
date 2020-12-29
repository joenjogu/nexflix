package com.joenjogu.nexflix.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.joenjogu.nexflix.models.Movie
import com.joenjogu.nexflix.models.TrendingMovie
import com.joenjogu.nexflix.toDomain

class MovieRepository(
        private val apiService: MoviesApiService,
        private val movieDao: MovieDao,
        private val trendingMovieDao: TrendingMovieDao) {

    val movies: LiveData<List<Movie>> = movieDao.getAllMovies()
    val trendingMovie: LiveData<List<TrendingMovie>> = trendingMovieDao.getAllMovies()

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
            movieDao.insertAllMovies(movies)
            return movies
        } catch (exception: Throwable){
            Log.e("Repo", "getPopularMovies: ", exception)
        }
        return movies
    }

    suspend fun getTrendingMovies(): MutableList<TrendingMovie> {
        val trendingMovies = mutableListOf<TrendingMovie>()
        try {
            val response = apiService.getTrendingMovies("2d9aa26f9b71ca6d8a3db85d730e19a4")
            val results = response.trendingResults
            for (result in results) {
                trendingMovies.add(result.toDomain())
            }
            trendingMovieDao.insertAllMovies(trendingMovies)
            return trendingMovies
        } catch (exception: Throwable) {
            Log.e("Repo", "getTrendingMovies: ", exception)
        }
        return trendingMovies
    }
}
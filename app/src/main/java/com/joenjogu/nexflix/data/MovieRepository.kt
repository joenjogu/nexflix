package com.joenjogu.nexflix.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.joenjogu.nexflix.models.Movie
import com.joenjogu.nexflix.models.RecommendedMovie
import com.joenjogu.nexflix.models.TrendingMovie
import com.joenjogu.nexflix.utils.toDomain

class MovieRepository(
        private val apiService: MoviesApiService,
        private val movieDao: MovieDao,
        private val trendingMovieDao: TrendingMovieDao,
        private val recommendedMovieDao: RecommendedMovieDao) {

    val movies: LiveData<List<Movie>> = movieDao.getAllMovies()
    val trendingMovie: LiveData<List<TrendingMovie>> = trendingMovieDao.getAllMovies()
    val recommendedMovies: LiveData<List<RecommendedMovie>> = recommendedMovieDao.getAllMovies()

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    suspend fun getMovie(id: Int): Movie {
        val repoMovie = movieDao.getMovieById(id)
        Log.d("Repo", "getMovie: movie ID $id, ${repoMovie}")

        if ( repoMovie != null) {
            return repoMovie
        } else {
            try {
                //fix live data
                val result = apiService.getMovie(id, "2d9aa26f9b71ca6d8a3db85d730e19a4")
                return result.toDomain()
            } catch (exception: Throwable) {
                Log.e("GetMovie", "getMovie: ", exception)
            }
            return Movie(0,
                    "https://image.tmdb.org/t/p/w500//biznhvfedHPp9GKjlVFXH6OZtyU.jpg",
                    "NULL", "NULL", 0.0, "NULL")
        }
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

    suspend fun getRecommendedMovies(movieId: Int): MutableList<Movie> {
        val recommendedMovies = mutableListOf<Movie>()
        try {
            val response = apiService.getRecommendedMovies(movieId, "2d9aa26f9b71ca6d8a3db85d730e19a4")
            val results = response.recommendationResults
            for (result in results) {
                recommendedMovies.add(result.toDomain())
            }
            movieDao.insertAllMovies(recommendedMovies)
        } catch (exception: Throwable) {
            Log.e("getRecommendedMovies", "getRecommendedMovies: ", exception)
        }
        return recommendedMovies
    }
}
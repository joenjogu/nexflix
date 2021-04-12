package com.joenjogu.nexflix.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.joenjogu.nexflix.models.Movie
import com.joenjogu.nexflix.utils.Category
import com.joenjogu.nexflix.utils.toPopularDomain
import com.joenjogu.nexflix.utils.toTrendingDomain

class MovieRepository(
        private val apiService: MoviesApiService,
        private val movieDao: MovieDao) {

    val popularMovies: LiveData<List<Movie>> = movieDao.getAllMovies(Category.TopRated)
    val trendingMovies: LiveData<List<Movie>> = movieDao.getAllMovies(Category.Trending)

    fun getRecommendationsFromDB(id: Int): LiveData<List<Movie>> {
        return movieDao.getRecommendedMovies(id)
    }

    suspend fun getMovie(id: Int): Movie {
        val repoMovie = movieDao.getMovieById(id)

        if ( repoMovie != null) {
            Log.d("Repo", "getMovie: movie ID $id, $repoMovie")
            getRecommendationsFromDB(id)
            Log.d("Repo", "getMovie: getting recommended movies" )
            return repoMovie
        } else {
            try {
                //fix recommendation id vs movie id
                val response = apiService.getMovie(id)
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null) {
                        return result.toPopularDomain()
                    }
                }

            } catch (exception: Throwable) {
                Log.e("GetMovie", "getMovie: ", exception)
            }
            return Movie(0,
                    "https://image.tmdb.org/t/p/w500//biznhvfedHPp9GKjlVFXH6OZtyU.jpg",
                    "NULL", "NULL", 0.0, "NULL", Category.Recommended)
        }
    }

    suspend fun getPopularMovies(): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        try {
            val response = apiService.getTopMovies()
            if (response.isSuccessful) {
                val results = response.body()
                if (results != null) {
                    for (result in results.movieResults) {
                        movies.add(result.toPopularDomain())
                    }
                }
                Log.d("Repo", "getPopularMovies: $movies")
                movieDao.insertAllMovies(movies)
                return movies
            }

        } catch (exception: Throwable){
            Log.e("Repo", "getPopularMovies: ", exception)
        }
        return movies
    }

    suspend fun getTrendingMovies(): MutableList<Movie> {
        val trendingMovies = mutableListOf<Movie>()
        try {
            val response = apiService.getTrendingMovies()
            if (response.isSuccessful) {
                val results = response.body()
                if (results != null) {
                    for (result in results.trendingResults) {
                        trendingMovies.add(result.toTrendingDomain())
                    }
                }
                Log.d("Repo", "getTrendingMovies: $trendingMovies")
                movieDao.insertAllMovies(trendingMovies)
                return trendingMovies
            }
        } catch (exception: Throwable) {
            Log.e("Repo", "getTrendingMovies: ", exception)
        }
        return trendingMovies
    }

    suspend fun getRecommendedMovies(movieId: Int): MutableList<Movie> {
        val recommendedMovies = mutableListOf<Movie>()
        try {
            val response = apiService.getRecommendedMovies(movieId)
            if (response.isSuccessful) {
                val results = response.body()
                if (results != null) {
                    for (result in results.movieResults) {
                        recommendedMovies.add(result.toPopularDomain())
                    }
                }
                Log.d("Repo", "getRecommendedMovies: $recommendedMovies")
                movieDao.insertAllMovies(recommendedMovies)
                return recommendedMovies
            }
        } catch (exception: Throwable) {
            Log.e("getRecommendedMovies", "getRecommendedMovies: ", exception)
        }
        return recommendedMovies
    }
}
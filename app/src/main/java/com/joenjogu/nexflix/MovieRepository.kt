package com.joenjogu.nexflix

import android.util.Log

class MovieRepository(private val apiService: MoviesApiService) {

    suspend fun getMovie(id: Int) {
        val response = apiService.getMovie(id)
    }

    suspend fun getPopularMovies(): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        try {
            val response = apiService.getLatestMovies("2d9aa26f9b71ca6d8a3db85d730e19a4")
            val results = response.results
            for (result in results) {
                val urlPrefix = "https://image.tmdb.org/t/p/w500/"
                val movie = Movie(
                    result.id,
                    urlPrefix + result.poster_path,
                    result.title,
                    result.overview,
                    result.vote_average,
                    result.release_date)
                movies.add(movie)
            }
            Log.d("Repo", "getPopularMovies: $movies")
            return movies
        } catch (exception: Throwable){
            Log.e("Repo", "getPopularMovies: ", exception)
        }
        return movies
    }

    suspend fun getTopRatedMovies() {

    }
}
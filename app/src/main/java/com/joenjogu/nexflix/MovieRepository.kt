package com.joenjogu.nexflix

class MovieRepository(private val apiService: MoviesApiService) {

    suspend fun getMovies() {
        val response = apiService.getMovie(21)
    }
}
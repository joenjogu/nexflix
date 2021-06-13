package com.joenjogu.nexflix.data

import com.joenjogu.nexflix.data.core.BaseDataSource

class MoviesRemoteDataSource(private val apiService: MoviesApiService) : BaseDataSource() {

    suspend fun fetchMovie(movieId: Int) = getNetworkResult { apiService.getMovie(movieId) }

    suspend fun fetchRecommendedMovies(movieId: Int) =
        getNetworkResult { apiService.getRecommendedMovies(movieId) }
}
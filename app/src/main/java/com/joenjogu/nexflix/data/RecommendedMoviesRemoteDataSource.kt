package com.joenjogu.nexflix.data

import com.joenjogu.nexflix.data.core.BaseDataSource

class RecommendedMoviesRemoteDataSource(private val apiService: MoviesApiService) : BaseDataSource() {

    suspend fun fetchRecommendedMovies(movieId: Int) = getNetworkResult { apiService.getRecommendedMovies(movieId) }
}
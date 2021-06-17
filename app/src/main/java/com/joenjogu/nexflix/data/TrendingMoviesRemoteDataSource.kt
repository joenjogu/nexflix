package com.joenjogu.nexflix.data

import com.joenjogu.nexflix.data.core.BaseDataSource

class TrendingMoviesRemoteDataSource(private val apiService: MoviesApiService) : BaseDataSource() {

    suspend fun fetchTrendingMovies() = getNetworkResult { apiService.getTrendingMovies() }
}
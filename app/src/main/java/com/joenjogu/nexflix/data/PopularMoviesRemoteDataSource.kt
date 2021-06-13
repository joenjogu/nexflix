package com.joenjogu.nexflix.data

import com.joenjogu.nexflix.data.core.BaseDataSource

class PopularMoviesRemoteDataSource(private val apiService: MoviesApiService) : BaseDataSource() {

    suspend fun fetchPopularMovies() = getNetworkResult { apiService.getTopMovies() }
}
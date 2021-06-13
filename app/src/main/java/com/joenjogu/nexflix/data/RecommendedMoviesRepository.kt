package com.joenjogu.nexflix.data

import com.joenjogu.nexflix.data.core.resultLiveData
import com.joenjogu.nexflix.utils.toRecommendedDomain

class RecommendedMoviesRepository(
    private val dao: MovieDao,
    private val remoteDataSource: RecommendedMoviesRemoteDataSource
) {
    fun getRecommendedMoviesResult(movieId: Int) = resultLiveData(
        databaseQuery = { dao.getRecommendedMovies(movieId) },
        networkCall = { remoteDataSource.fetchRecommendedMovies(movieId) },
        saveCallToDb = { searchResponse ->
            dao.insertAllMovies(searchResponse.movieResults.map {
                it.toRecommendedDomain(movieId)
            })
        }
    )
}
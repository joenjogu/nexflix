package com.joenjogu.nexflix.data.core

import com.joenjogu.nexflix.data.MovieDao
import com.joenjogu.nexflix.data.TrendingMoviesRemoteDataSource
import com.joenjogu.nexflix.utils.Category
import com.joenjogu.nexflix.utils.toTrendingDomain

class TrendingMoviesRepository(
    private val dao: MovieDao,
    private val remoteDataSource: TrendingMoviesRemoteDataSource
) {
    val trendingMoviesResult = resultLiveData(
        databaseQuery = { dao.getAllMovies(Category.Trending) },
        networkCall = { remoteDataSource.fetchTrendingMovies() },
        saveCallToDb = { trendingResponse ->
            dao.insertAllMovies(trendingResponse.trendingResults.map {
                it.toTrendingDomain()
            })
        }
    )
}
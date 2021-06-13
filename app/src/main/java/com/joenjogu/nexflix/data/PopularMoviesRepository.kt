package com.joenjogu.nexflix.data

import com.joenjogu.nexflix.data.core.resultLiveData
import com.joenjogu.nexflix.utils.Category
import com.joenjogu.nexflix.utils.toPopularDomain

class PopularMoviesRepository(
    private val dao: MovieDao,
    private val remoteDataSource: PopularMoviesRemoteDataSource
) {
    val popularMoviesResult = resultLiveData(
        databaseQuery = { dao.getAllMovies(Category.TopRated) },
        networkCall = { remoteDataSource.fetchPopularMovies() },
        saveCallToDb = { searchResponse ->
            dao.insertAllMovies(searchResponse.movieResults.map {
                it.toPopularDomain()
            })
        }
    )
}
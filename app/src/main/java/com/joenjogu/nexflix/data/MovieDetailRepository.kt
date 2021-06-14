package com.joenjogu.nexflix.data

import com.joenjogu.nexflix.data.core.resultLiveData
import com.joenjogu.nexflix.models.MovieFavouriteUpdate
import com.joenjogu.nexflix.utils.Category
import com.joenjogu.nexflix.utils.toPopularDomain
import com.joenjogu.nexflix.utils.toRecommendedDomain

class MovieDetailRepository(
    private val dao: MovieDao,
    private val remoteDataSource: MoviesRemoteDataSource
) {
    fun getMovieResult(movieId: Int) = resultLiveData(
        databaseQuery = { dao.getMovieById(movieId) },
        networkCall = { remoteDataSource.fetchMovie(movieId) },
        saveCallToDb = { dao.insertAllMovies(listOf(it.toPopularDomain())) }
    )

    fun getRecommendedMoviesResult(movieId: Int) = resultLiveData(
        databaseQuery = { dao.getRecommendedMovies(movieId) },
        networkCall = { remoteDataSource.fetchRecommendedMovies(movieId) },
        saveCallToDb = { searchResponse ->
            dao.insertAllMovies(searchResponse.movieResults.map {
                it.toRecommendedDomain(movieId)
            })
        }
    )

    suspend fun setFavourite(movieId: Int) =
        dao.updateFavourite(MovieFavouriteUpdate(movieId, Category.TopRated, true))
}
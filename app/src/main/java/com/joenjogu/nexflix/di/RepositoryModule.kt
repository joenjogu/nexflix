package com.joenjogu.nexflix.di

import com.joenjogu.nexflix.data.*
import com.joenjogu.nexflix.data.core.TrendingMoviesRepository
import org.koin.dsl.module

val repositoryModule = module {

    single {
        MovieRepository(
            apiService = get(),
            movieDao = get()
        )
    }

    single {
        MovieDetailRepository(
            dao = get(),
            remoteDataSource = get()
        )
    }

    single {
        PopularMoviesRepository(
            dao = get(),
            remoteDataSource = get()
        )
    }

    single {
        TrendingMoviesRepository(
            dao = get(),
            remoteDataSource = get()
        )
    }

    single { PopularMoviesRemoteDataSource(apiService = get()) }

    single { TrendingMoviesRemoteDataSource(apiService = get()) }

    single { RecommendedMoviesRemoteDataSource(apiService = get()) }

    single { MoviesRemoteDataSource(apiService = get()) }
}

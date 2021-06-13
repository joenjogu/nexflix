package com.joenjogu.nexflix.di

import com.joenjogu.nexflix.data.MovieRepository
import com.joenjogu.nexflix.data.PopularMoviesRepository
import com.joenjogu.nexflix.data.RecommendedMoviesRepository
import com.joenjogu.nexflix.data.SingleMovieRepository
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
        SingleMovieRepository(
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

    single {
        RecommendedMoviesRepository(
            dao = get(),
            remoteDataSource = get()
        )
    }
}

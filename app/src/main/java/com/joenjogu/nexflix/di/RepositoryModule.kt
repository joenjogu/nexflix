package com.joenjogu.nexflix.di

import com.joenjogu.nexflix.data.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { MovieRepository(
            apiService = get(),
            movieDao = get()
    )
    }
}
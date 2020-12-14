package com.joenjogu.nexflix

import org.koin.dsl.module

val repositoryModule = module {

    single { MovieRepository(apiService = get(), movieDao = get()) }
}
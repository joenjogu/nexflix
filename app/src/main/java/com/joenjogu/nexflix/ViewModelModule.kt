package com.joenjogu.nexflix

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        PopularMovieViewModel(repository = get())
    }
}
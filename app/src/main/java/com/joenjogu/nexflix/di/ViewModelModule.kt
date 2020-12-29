package com.joenjogu.nexflix

import com.joenjogu.nexflix.viewmodels.LatestMovieViewModel
import com.joenjogu.nexflix.viewmodels.PopularMovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        PopularMovieViewModel(repository = get())
    }

    viewModel {
        LatestMovieViewModel(repository = get())
    }
}
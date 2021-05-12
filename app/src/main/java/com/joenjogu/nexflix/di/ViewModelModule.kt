package com.joenjogu.nexflix.di

import com.joenjogu.nexflix.viewmodels.FavouriteMovieViewModel
import com.joenjogu.nexflix.viewmodels.LatestMovieViewModel
import com.joenjogu.nexflix.viewmodels.MovieDetailViewModel
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

    viewModel {
        (id: String) ->
        MovieDetailViewModel(repository = get(), id)
    }

    viewModel {
        FavouriteMovieViewModel(repository = get())
    }
}

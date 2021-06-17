package com.joenjogu.nexflix.di

import com.joenjogu.nexflix.viewmodels.*
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

    viewModel {
        ViewPagerViewModel(repository = get())
    }
}

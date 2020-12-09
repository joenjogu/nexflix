package com.joenjogu.nexflix

import androidx.lifecycle.ViewModelProvider

object Injection {
    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return PopularMovieViewModelFactory(MoviesApiService.createService())
    }
}
package com.joenjogu.nexflix

import android.content.Context
import androidx.lifecycle.ViewModelProvider

object Injection {
    fun provideMovieRepository(context: Context): MovieRepository {
        return MovieRepository(MoviesApiService.createService(), MovieDatabase.getDatabase(context))
    }
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return PopularMovieViewModelFactory(provideMovieRepository(context))
    }
}
package com.joenjogu.nexflix

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class PopularMovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularMovieViewModel::class.java)) {
            return PopularMovieViewModel(repository) as T
        }
        throw IllegalArgumentException("No viewmodel")
    }
}
package com.joenjogu.nexflix.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joenjogu.nexflix.data.MovieRepository
import kotlinx.coroutines.launch
import java.io.IOException

class PopularMovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val movies = repository.popularMovies

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                repository.getPopularMovies()
            } catch (networkError: IOException) {
                throw networkError
            }
        }
    }
}

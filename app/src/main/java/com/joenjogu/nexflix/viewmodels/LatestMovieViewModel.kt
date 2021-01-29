package com.joenjogu.nexflix.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joenjogu.nexflix.data.MovieRepository
import kotlinx.coroutines.launch
import java.io.IOException

class LatestMovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val trendingMovie = repository.trendingMovies

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                repository.getTrendingMovies()
            } catch (exception: IOException) {
                Log.e("LatestMovieViewModel", "refreshDataFromRepository: Failed to refresh data")
                throw exception
            }

        }
    }
}
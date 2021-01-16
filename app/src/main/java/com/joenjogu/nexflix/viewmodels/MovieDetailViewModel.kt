package com.joenjogu.nexflix.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joenjogu.nexflix.data.MovieRepository
import com.joenjogu.nexflix.models.Movie
import com.joenjogu.nexflix.models.RecommendedMovie
import kotlinx.coroutines.launch
import java.io.IOException

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {

    // get movie id from safArgs
    val id: String = "155"
    val movieId = id.toInt()
    val movie = getMovieById(movieId)
    val recommendedMovies = repository.recommendedMovies

    init {
        refreshDataFromRepository(movieId)
    }

    private fun getMovieById(movieId: Int): LiveData<Movie> {
        Log.d("MovieDetailViewModel", "getMovieById: ${repository.getMovie(movieId)}")
        return repository.getMovie(movieId)
    }

    private fun refreshDataFromRepository(movieId: Int) {
        viewModelScope.launch {
            try {
                repository.getRecommendedMovies(movieId)
            } catch (networkError: IOException) {
                throw networkError
            }
        }
    }
}
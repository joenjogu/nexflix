package com.joenjogu.nexflix.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joenjogu.nexflix.data.MovieRepository
import com.joenjogu.nexflix.models.Movie
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieRepository, val id: String) : ViewModel() {

    // get movie id from safArgs
    val movieId = id.toInt()
    val recommendedMovies = repository.getRecommendationsFromDB(movieId)

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    init {
        getMovieById(movieId)
    }

    private fun getMovieById(movieId: Int) {
        viewModelScope.launch {
            val movie = repository.getMovie(movieId)
            Log.d("MovieDetailViewModel", "getMovieById: Movie: $movie")
            _movie.value = movie
        }
    }

    fun setFavourite() {
        viewModelScope.launch { repository.setFavourite(movieId) }
    }
}

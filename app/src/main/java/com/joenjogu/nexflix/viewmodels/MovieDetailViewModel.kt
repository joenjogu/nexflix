package com.joenjogu.nexflix.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joenjogu.nexflix.data.MovieDetailRepository
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieDetailRepository, val id: String) : ViewModel() {

    // get movie id from safArgs
    val movieId = id.toInt()
    val movieResult = repository.getMovieResult(movieId)
    val recommendedMoviesResult = repository.getRecommendedMoviesResult(movieId)
//    val recommendedMovies = repository.getRecommendationsFromDB(movieId)
//
//    private val _movie = MutableLiveData<Movie>()
//    val movie: LiveData<Movie>
//        get() = _movie
//
//    init {
//        getMovieById(movieId)
//    }
//
//    private fun getMovieById(movieId: Int) {
//        viewModelScope.launch {
//            val movie = repository.getMovie(movieId)
//            Log.d("MovieDetailViewModel", "getMovieById: Movie: $movie")
//            _movie.value = movie
//        }
//    }

    fun setFavourite() {
        viewModelScope.launch { repository.setFavourite(movieId) }
    }
}

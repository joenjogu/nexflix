package com.joenjogu.nexflix.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joenjogu.nexflix.data.MovieRepository
import com.joenjogu.nexflix.models.Movie
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {
    // get movie id from safArgs
    val movie = getMovieById(id = 0)

    private fun getMovieById (id: Int): LiveData<Movie> {
        return repository.getMovie(id)
    }
}
package com.joenjogu.nexflix.viewmodels

import androidx.lifecycle.ViewModel
import com.joenjogu.nexflix.data.MovieRepository

class FavouriteMovieViewModel(val repository: MovieRepository) : ViewModel() {

    val favouriteMovies = repository.favouriteMovies
}

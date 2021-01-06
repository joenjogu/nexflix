package com.joenjogu.nexflix.viewmodels

import androidx.lifecycle.ViewModel
import com.joenjogu.nexflix.data.MovieRepository

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {
    // configure refresh strategy from repository
    val movie = repository.movie
}
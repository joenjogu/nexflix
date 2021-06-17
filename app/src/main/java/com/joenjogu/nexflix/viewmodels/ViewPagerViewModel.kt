package com.joenjogu.nexflix.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.joenjogu.nexflix.data.MovieRepository
import com.joenjogu.nexflix.models.Movie

class ViewPagerViewModel(private val repository: MovieRepository) : ViewModel() {

    var searchResults: LiveData<List<Movie>>? = null

    fun searchMovie(movie: String): LiveData<List<Movie>> {
        val results = repository.searchMovie(movie)
        searchResults = results
        return results
    }
}
package com.joenjogu.nexflix.viewmodels

import androidx.lifecycle.ViewModel
import com.joenjogu.nexflix.data.PopularMoviesRepository

class PopularMovieViewModel(repository: PopularMoviesRepository) : ViewModel() {

    val popularMoviesResult = repository.popularMoviesResult

//    val movies = repository.popularMovies
//
//    init {
//        refreshDataFromRepository()
//    }
//
//    private fun refreshDataFromRepository() {
//        viewModelScope.launch {
//            try {
//                repository.getPopularMovies()
//            } catch (networkError: IOException) {
//                throw networkError
//            }
//        }
//    }
}

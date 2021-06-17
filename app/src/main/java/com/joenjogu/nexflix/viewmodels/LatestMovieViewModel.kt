package com.joenjogu.nexflix.viewmodels

import androidx.lifecycle.ViewModel
import com.joenjogu.nexflix.data.core.TrendingMoviesRepository

class LatestMovieViewModel(private val repository: TrendingMoviesRepository) : ViewModel() {

    val trendingMovies = repository.trendingMoviesResult

//    val trendingMovie = repository.trendingMovies
//
//    init {
//        refreshDataFromRepository()
//    }
//
//    private fun refreshDataFromRepository() {
//        viewModelScope.launch {
//            try {
//                repository.getTrendingMovies()
//            } catch (exception: IOException) {
//                Log.e("LatestMovieViewModel", "refreshDataFromRepository: Failed to refresh data")
//                throw exception
//            }
//        }
//    }
}

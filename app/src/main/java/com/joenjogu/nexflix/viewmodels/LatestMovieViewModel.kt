package com.joenjogu.nexflix.viewmodels

import androidx.lifecycle.ViewModel
import com.joenjogu.nexflix.data.core.TrendingMoviesRepository

class LatestMovieViewModel(repository: TrendingMoviesRepository) : ViewModel() {

    val trendingMovies = repository.trendingMoviesResult

}

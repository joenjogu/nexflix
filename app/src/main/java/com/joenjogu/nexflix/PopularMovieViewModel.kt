package com.joenjogu.nexflix

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PopularMovieViewModel(private val apiService: MoviesApiService) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()

    val movies: LiveData<List<Movie>> =
        _movies

    init {
        getLatestMovies()
    }

    fun getLatestMovies() {
        viewModelScope.launch {
            try {
                val response = apiService.getLatestMovies("2d9aa26f9b71ca6d8a3db85d730e19a4")
                val results = response.results
                val movies = mutableListOf<Movie>()
                for (result in results) {
                    val urlPrefix = "https://image.tmdb.org/t/p/w500/"
                    val movie = Movie(
                        result.id,
                        urlPrefix + result.poster_path,
                        result.title,
                        result.overview,
                        result.vote_average,
                        result.release_date)
                    movies.add(movie)
                }
                _movies.value = movies
                Log.d("Viewmodel", "getMovie: $response")
            } catch (exception: Throwable){
                Log.e("Viewmodel", "getMovie: ", exception)
            }
        }

    }
}
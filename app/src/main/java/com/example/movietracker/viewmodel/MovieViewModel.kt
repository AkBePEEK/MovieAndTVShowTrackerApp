package com.example.movietracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietracker.data.api.RetrofitInstance
import com.example.movietracker.data.repository.MovieRepository
import com.example.movietracker.model.Movie
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies
    private val _movieDetails = MutableLiveData<Movie>()
    val movieDetails: LiveData<Movie> = _movieDetails

    fun fetchPopularMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPopularMovies(apiKey)
                _movies.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addToFavorites(movieId: Int, title: String, posterPath: String, type: String) {
        viewModelScope.launch {
            repository.addToWatchlist(movieId, title, posterPath, type)
        }
    }

    fun getMovieDetails(movieId: Int, apiKey: String) {
        viewModelScope.launch {
            try {
                val movie = repository.getMovieDetails(movieId, apiKey)
                _movieDetails.postValue(movie)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchMovies(apiKey: String, query: String) {
        viewModelScope.launch {
            val result = repository.searchMovies(apiKey, query)
            _movies.value = result
        }
    }
}
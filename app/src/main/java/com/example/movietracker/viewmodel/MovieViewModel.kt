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

    fun fetchPopularMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPopularMovies(apiKey)
                _movies.value = response.results
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun addToFavorites(movieId: Int, title: Any, s: String) {

    }

    fun searchMovies(s: String, it: String) {

    }
}
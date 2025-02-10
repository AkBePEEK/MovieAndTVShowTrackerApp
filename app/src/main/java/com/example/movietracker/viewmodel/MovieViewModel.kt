package com.example.movietracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movietracker.data.api.RetrofitInstance
import com.example.movietracker.data.repository.MovieRepository
import com.example.movietracker.model.Movie
import com.example.movietracker.ui.common.MoviePagingSource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    val pagedMovies: Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { MoviePagingSource(repository) }
    ).flow

    private val _movieDetails = MutableStateFlow<Movie?>(null)
    val movieDetails: StateFlow<Movie?> = _movieDetails.asStateFlow()

    fun fetchPopularMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPopularMovies(apiKey)
                if (response.isSuccessful) {
                    _movies.value = response.results
                } else {
                    _movies.value = emptyList()
                }
            } catch (e: Exception) {
                _movies.value = emptyList()
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
                _movieDetails.value = movie
            } catch (e: Exception) {
                _movieDetails.value = Movie(
                    id = 0,
                    title = "No title",
                    overview = "No overview",
                    posterPath = "",
                    backdropPath = null,
                    releaseDate = "TBD",
                    voteAverage = 0.0,
                    voteCount = 0,
                    genreIds = emptyList()
                )
                e.printStackTrace()
            }
        }
    }

    fun searchMovies(apiKey: String, query: String) {
        viewModelScope.launch {
            try {
                val result = repository.searchMovies(apiKey, query)
                _movies.value = result
            } catch (e: Exception) {
                _movies.value = emptyList()
                e.printStackTrace()
            }
        }
    }
}

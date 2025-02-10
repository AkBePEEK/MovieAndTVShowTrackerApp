package com.example.movietracker.data.repository

import com.example.movietracker.data.api.MovieApi
import com.example.movietracker.data.db.MovieDao
import com.example.movietracker.model.Favorite
import com.example.movietracker.model.Movie
import com.example.movietracker.model.WatchlistItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieApi,
    private val movieDao: MovieDao,
    private val movieApi: MovieApi
) {
    suspend fun getMovieDetails(movieId: Int, apiKey: String): Movie {
        return api.getMovieDetails(movieId, apiKey)
    }

    suspend fun addToWatchlist(movieId: Int, title: String, posterPath: String, type: String) {
        movieDao.insertWatchlistItem(WatchlistItem(movieId, title, posterPath, type))
    }

    suspend fun removeWatchlistItem(watchlistItem: WatchlistItem){
        movieDao.deleteWatchlistItem(watchlistItem)
    }

    fun getWatchlist() = movieDao.getWatchlist()

    suspend fun addToFavorites(id: Int, title: String, posterPath: String, type: String) {
        movieDao.insertFavorite(Favorite(id, title, posterPath, type))
    }

    suspend fun removeFavorite(favorite: Favorite) {
        movieDao.removeFavorite(favorite)
    }

    fun getFavorites(): Flow<List<Favorite>> = movieDao.getFavorites()

    suspend fun searchMovies(apiKey: String, query: String): List<Movie> {
        // Replace this with the actual API call
        return movieApi.searchMovies(query, apiKey)
    }
}
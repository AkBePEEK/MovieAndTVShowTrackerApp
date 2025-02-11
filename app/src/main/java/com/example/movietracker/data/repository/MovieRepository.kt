package com.example.movietracker.data.repository

import com.example.movietracker.data.api.MovieApi
import com.example.movietracker.data.db.MovieDao
import com.example.movietracker.model.Favorite
import com.example.movietracker.model.Movie
import com.example.movietracker.model.MovieResponse
import com.example.movietracker.model.WatchlistItem
import com.example.movietracker.model.TvShow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val movieApi: MovieApi
) {
    suspend fun getMovieDetails(movieId: Int, apiKey: String): Movie {
        return movieApi.getMovieDetails(movieId, apiKey)
    }

    suspend fun addToWatchlist(movieId: Int, title: String, posterPath: String, type: String) {
        movieDao.insertWatchlistItem(WatchlistItem(movieId, title, posterPath, type))
    }

    suspend fun removeWatchlistItem(watchlistItem: WatchlistItem){
        movieDao.deleteWatchlistItem(watchlistItem)
    }

    fun getWatchlist() = movieDao.getWatchlist()

    suspend fun addToFavorites(movie: Movie, type: String) {
        movieDao.insertFavorite(Favorite(
            movie.id, movie.title, movie.posterPath, type, movie.overview, movie.posterPath,
            movie.releaseDate, movie.voteAverage, movie.voteCount, movie.genreIds
        ))
    }

    suspend fun removeFavorite(favorite: Favorite) {
        movieDao.removeFavorite(favorite)
    }

    fun getFavorites(): Flow<List<Favorite>> = movieDao.getFavorites()

    suspend fun searchMovies(apiKey: String, query: String): MovieResponse {
        // Replace this with the actual API call
        return movieApi.searchMovies(query, apiKey)
    }

    suspend fun getPopularMovies(apiKey: String): List<Movie> {
        return movieApi.getPopularMovies(apiKey).results
    }

    suspend fun getPopularMoviesPage(page: Int): MovieResponse {
        return movieApi.getPopularMoviesPage("YOUR_API_KEY", page)
    }

    suspend fun getPopularTvShows(apiKey: String): List<TvShow> {
        return movieApi.getPopularTvShows(apiKey).results
    }
}
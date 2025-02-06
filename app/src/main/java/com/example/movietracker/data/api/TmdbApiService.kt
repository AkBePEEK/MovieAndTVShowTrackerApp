package com.example.movietracker.data.api


import com.example.movietracker.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface TmdbApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieResponse

    @GET("search/movie")
    suspend fun searchMovies(@Query("api_key") apiKey: String, @Query("query") query: String): MovieResponse
}
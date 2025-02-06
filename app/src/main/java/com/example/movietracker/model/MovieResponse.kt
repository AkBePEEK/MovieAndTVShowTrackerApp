package com.example.movietracker.model

data class MovieResponse(
    val page: Int,                // Current page of results
    val results: List<Movie>,     // List of movies
    val totalPages: Int,          // Total number of pages
    val totalResults: Int         // Total number of results
)
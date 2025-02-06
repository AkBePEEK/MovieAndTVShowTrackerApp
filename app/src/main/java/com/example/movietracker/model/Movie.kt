package com.example.movietracker.model

data class Movie(
    val id: Int,                  // Unique ID of the movie
    val title: String,            // Title of the movie
    val overview: String,         // Synopsis or description
    val posterPath: String?,      // Path to the poster image (e.g., "/abc123.jpg")
    val backdropPath: String?,    // Path to the backdrop image
    val releaseDate: String,      // Release date (e.g., "2023-10-15")
    val voteAverage: Double,      // Average rating (e.g., 7.5)
    val voteCount: Int,           // Number of votes
    val genreIds: List<Int>?      // List of genre IDs
)
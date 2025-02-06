package com.example.movietracker.model

data class TvShowResponse(
    val page: Int,                // Current page of results
    val results: List<TvShow>,    // List of TV shows
    val totalPages: Int,          // Total number of pages
    val totalResults: Int         // Total number of results
)
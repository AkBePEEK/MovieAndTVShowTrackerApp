package com.example.movietracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey val id: Int,      // Unique ID of the movie/TV show
    val title: String,            // Title of the movie/TV show
    val posterPath: String?,      // Path to the poster image
    val type: String              // Type ("movie" or "tv")
)
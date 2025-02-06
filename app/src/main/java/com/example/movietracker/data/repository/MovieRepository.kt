package com.example.movietracker.data.repository

import androidx.lifecycle.LiveData
import com.example.movietracker.data.db.AppDatabase
import com.example.movietracker.model.Favorite

class MovieRepository(private val db: AppDatabase) {
    suspend fun addFavorite(favorite: Favorite) {
        db.favoriteDao().addFavorite(favorite)
    }

    fun getFavorites(): LiveData<List<Favorite>> {
        return db.favoriteDao().getAllFavorites()
    }
}
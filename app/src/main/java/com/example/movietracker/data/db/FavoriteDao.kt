package com.example.movietracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movietracker.model.Favorite

@Dao
interface FavoriteDao {
    @Insert
    suspend fun addFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): LiveData<List<Favorite>>
}
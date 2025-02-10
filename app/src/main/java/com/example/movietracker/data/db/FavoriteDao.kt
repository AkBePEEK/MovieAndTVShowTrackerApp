package com.example.movietracker.data.db

import androidx.room.*
import com.example.movietracker.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Favorite)  // Add @Insert annotation here

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<Favorite>>  // Use Flow instead of LiveData
}

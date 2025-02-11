package com.example.movietracker.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromGenreIdsList(genreIds: List<Int>?): String {
        return Gson().toJson(genreIds)
    }

    @TypeConverter
    fun toGenreIdsList(genreIdsString: String?): List<Int> {
        val type = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(genreIdsString, type) ?: emptyList()
    }
}
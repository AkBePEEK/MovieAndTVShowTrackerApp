package com.example.movietracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietracker.data.repository.MovieRepository
import com.example.movietracker.model.Favorite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val favorites: Flow<List<Favorite>> = repository.getFavorites()

    fun removeFromFavorites(favorite: Favorite) {
        viewModelScope.launch {
            repository.removeFavorite(favorite)
        }
    }
}
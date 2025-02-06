package com.example.movietracker.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietracker.data.repository.MovieRepository
import com.example.movietracker.model.Favorite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.text.Typography.dagger

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val favorites: LiveData<List<Favorite>> = repository.getFavorites()

    fun removeFromFavorites(favorite: Favorite) {
        viewModelScope.launch {
            repository.removeFavorite(favorite)
        }
    }
}
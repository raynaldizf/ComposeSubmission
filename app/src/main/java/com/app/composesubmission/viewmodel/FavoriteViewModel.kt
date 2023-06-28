package com.app.composesubmission.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.composesubmission.model.BookRepository
import com.app.composesubmission.ui.screen.FavState
import com.app.composesubmission.ui.screen.UiState

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel (private val repository: BookRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<FavState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<FavState>> get() = _uiState

    fun getAddedFavorites() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedFavorites().collect {
                _uiState.value = UiState.Success(FavState(it))
            }
        }
    }
}
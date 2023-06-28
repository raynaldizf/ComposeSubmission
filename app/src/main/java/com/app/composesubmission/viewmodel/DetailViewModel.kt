package com.app.composesubmission.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.composesubmission.model.Book
import com.app.composesubmission.model.BookRepository
import com.app.composesubmission.model.Favorite
import com.app.composesubmission.ui.screen.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: BookRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Favorite>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Favorite>> get() = _uiState

    fun getBookById(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getFavoriteById(id))
        }
    }

    fun addFav(book: Book, status: Boolean) {
        viewModelScope.launch {
            repository.updateFavorite(book.id, status)
        }
    }
}
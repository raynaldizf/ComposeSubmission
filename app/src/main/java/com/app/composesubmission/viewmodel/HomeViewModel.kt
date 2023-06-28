package com.app.composesubmission.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.composesubmission.model.BookRepository
import com.app.composesubmission.model.Favorite
import com.app.composesubmission.ui.screen.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: BookRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Favorite>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Favorite>>> get() = _uiState

    private val _books = MutableStateFlow(repository.getResultFavorite()
        .sortedBy { it.book.judul }
        .groupBy { it.book.judul[0] }
    )
    val books: StateFlow<Map<Char, List<Favorite>>> get() = _books

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _books.value = repository.searchBook(_query.value)
            .sortedBy { it.book.judul }
            .groupBy { it.book.judul[0] }
    }

    fun getAllBooks() {
        viewModelScope.launch {
            repository.getAllBooks()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}
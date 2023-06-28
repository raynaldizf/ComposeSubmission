package com.app.composesubmission.model

import kotlinx.coroutines.flow.*

class BookRepository {

    private val favorite = mutableListOf<Favorite>()

    init {
        if (favorite.isEmpty()) {
            BookData.bookList.forEach {
                favorite.add(Favorite(0, false, it))
            }
        }
    }

    fun getAllBooks(): Flow<List<Favorite>> {
        return flowOf(favorite)
    }

    fun getResultFavorite(): List<Favorite> {
        return favorite
    }

    fun searchBook(query: String): List<Favorite>{
        return favorite.filter {
            it.book.judul.contains(query, ignoreCase = true)
        }
    }

    fun getFavoriteById(id: Int): Favorite {
        return favorite.first {
            it.book.id == id
        }
    }

    fun updateFavorite(id: Int, status: Boolean): Flow<Boolean> {
        val index = favorite.indexOfFirst { it.book.id == id }
        val result = if (!status) {
            val favIndex = favorite[index]
            favorite[index] = favIndex.copy(id = index, status = true, book = favIndex.book)
            true
        } else {
            val favIndex = favorite[index]
            favorite[index] = favIndex.copy(id = index, status = false, book = favIndex.book)
            false
        }
        return flowOf(result)
    }

    fun getAddedFavorites(): Flow<List<Favorite>> {
        return getAllBooks().map { books ->
            books.filter { book ->
                book.status
            }
        }
    }

    companion object {
        @Volatile
        private var instance: BookRepository? = null

        fun getInstance(): BookRepository =
            instance ?: synchronized(this) {
                BookRepository().apply {
                    instance = this
                }
            }
    }

}
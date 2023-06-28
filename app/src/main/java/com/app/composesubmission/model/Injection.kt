package com.app.composesubmission.model

object Injection {
    fun provideRepository(): BookRepository {
        return BookRepository.getInstance()
    }
}
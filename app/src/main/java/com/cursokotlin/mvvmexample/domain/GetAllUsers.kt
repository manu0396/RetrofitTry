package com.cursokotlin.mvvmexample.domain

import com.cursokotlin.mvvmexample.data.QuoteRepository
import com.cursokotlin.mvvmexample.data.model.QuoteModel
import javax.inject.Inject

class GetAllUsers @Inject constructor(private val repository: QuoteRepository) {
    suspend operator fun invoke() = repository.getAllUsers()

}
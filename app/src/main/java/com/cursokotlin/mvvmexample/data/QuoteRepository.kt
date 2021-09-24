package com.cursokotlin.mvvmexample.data

import com.cursokotlin.mvvmexample.data.model.QuoteModel
import com.cursokotlin.mvvmexample.data.model.QuoteProvider
import com.cursokotlin.mvvmexample.data.network.QuoteService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteProvider: QuoteProvider
) {

    suspend fun getAllUsers(): List<QuoteModel> {
        //extraemos los users de la api
        val response = api.getAllUsers()
            //Le pasamos el []c on el user al repository de la app.
        quoteProvider.allUsers = response
        return response
    }

    suspend fun getUserById(id: String): QuoteModel {
        //Recogemos el usuario pasandole el Id al QueryRepository
        val response = api.getUserById(id)
        quoteProvider.userById = response

        return response
    }
}
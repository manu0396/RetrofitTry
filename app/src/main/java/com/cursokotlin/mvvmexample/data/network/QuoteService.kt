package com.cursokotlin.mvvmexample.data.network

import com.cursokotlin.mvvmexample.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.emptyList as emptyList1

@Singleton
class QuoteService @Inject constructor(private val api:QuoteApiClient) {
    suspend fun getAllUsers(): List<QuoteModel> {

        return withContext(Dispatchers.IO) {
            val response = api.getAllUUsers()
            if(response.isSuccessful){
                response.body()
            }else{
                listOf<QuoteModel>()
            }
        } as List<QuoteModel>
    }

    suspend fun getUserById(id:String): QuoteModel{

        return withContext(Dispatchers.IO){
            val response = api.getUserById(id)
            if(response.isSuccessful){
                response.body()
            }else {
                listOf<QuoteModel>()
            }
        } as QuoteModel
    }
}
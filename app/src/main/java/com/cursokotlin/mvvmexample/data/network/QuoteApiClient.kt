package com.cursokotlin.mvvmexample.data.network

import com.cursokotlin.mvvmexample.data.model.QuoteModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface QuoteApiClient {
    @GET("users")
    suspend fun getAllUUsers(): Response<List<QuoteModel>>

    @GET
    suspend fun getUserById(@Url url:String): Response<QuoteModel>
    }

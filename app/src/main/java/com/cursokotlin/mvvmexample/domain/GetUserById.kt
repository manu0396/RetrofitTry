package com.cursokotlin.mvvmexample.domain

import android.util.Log
import com.cursokotlin.mvvmexample.data.QuoteRepository
import com.cursokotlin.mvvmexample.data.model.QuoteModel
import com.cursokotlin.mvvmexample.di.NetworkModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO() A este le pasaba el Provider, mientras que al getAll le pasaba el repository
class GetUserById @Inject constructor(private val repository: QuoteRepository) {
    var userSearch: QuoteModel? = null
    //TODO() Si no funciona probar a sacarlo del repository.
    suspend  operator fun invoke(id: String): QuoteModel? {

        //TODO("Cargar en userSearch los datos de getUserById")
        userSearch = repository.getUserById(id)
        return userSearch
        }
    }

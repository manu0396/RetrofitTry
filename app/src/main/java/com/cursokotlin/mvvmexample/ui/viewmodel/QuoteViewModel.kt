package com.cursokotlin.mvvmexample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursokotlin.mvvmexample.data.model.QuoteModel
import com.cursokotlin.mvvmexample.domain.GetAllUsers
import com.cursokotlin.mvvmexample.domain.GetUserById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getAllUsers:GetAllUsers,
    private val getUserById: GetUserById
) : ViewModel() {

    val quoteModel = MutableLiveData<QuoteModel?>()
    val quoteById = MutableLiveData<QuoteModel?>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getAllUsers()

            if(!result.isNullOrEmpty()){
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun getUserIdQuote(id: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val quote = getUserById(id)
            if (quote != null) {
                quoteById.postValue(quote)
            }
            isLoading.postValue(false)
        }
    }
}
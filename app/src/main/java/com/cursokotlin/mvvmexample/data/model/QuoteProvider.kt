package com.cursokotlin.mvvmexample.data.model

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.emptyList as emptyList1

@Singleton
class QuoteProvider @Inject constructor() {
    var allUsers: List<QuoteModel> = emptyList1()
    var userById: QuoteModel? = null

}
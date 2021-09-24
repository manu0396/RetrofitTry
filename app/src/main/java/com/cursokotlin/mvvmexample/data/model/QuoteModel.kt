package com.cursokotlin.mvvmexample.data.model

import com.google.gson.annotations.SerializedName

//TODO(sacar los atributos internos de lat lng y los detalles de la compañia.)
data class QuoteModel(
    @SerializedName("id") val quote: Number,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("address") val addrees: address,
    @SerializedName("phone") val phone: String,
    @SerializedName("website") val website: String,
    @SerializedName("company") val company: company,
)

object address {
    val street = ""
    val suite = ""
    val city = ""
    val zipcode = ""
    object geo{
        val lat = ""
        val lng = ""
    }
    override fun toString(): String {
        return super.toString()
    }
}
object company{
    val name = ""
    val catchPhrase = ""
    val bs = ""
}
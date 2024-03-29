package com.radlance.domain.entity

data class Market(
    val price: Double,
    val lastUpdate: String,
    val highDay: Double,
    val lowDay: Double,
    val fromSymbol: String,
    val toSymbol: String,
    val imageUrl: String
) {
    fun getFullImageUrl(): String {
        return BASE_URL + imageUrl
    }

    companion object {
        private const val BASE_URL = "https://www.cryptocompare.com/"
    }
}
package com.radlance.domain.entity

data class Market(
    val price: Double,
    val lastUpdate: String,
    val highDay: Double,
    val minDay: Double,
    val fromSymbol: String,
    val toSymbol: String
)
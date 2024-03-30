package com.radlance.data.network.pojo.market

import com.google.gson.annotations.SerializedName

data class CoinPriceInfoDTO(
    @SerializedName("PRICE")
    val price: Double,
    @SerializedName("LASTUPDATE")
    val lastUpdate: Long,
    @SerializedName("HIGHDAY")
    val highDay: Double,
    @SerializedName("LOWDAY")
    val lowDay: Double,
    @SerializedName("FROMSYMBOL")
    val fromSymbol: String,
    @SerializedName("TOSYMBOL")
    val toSymbol: String,
    @SerializedName("IMAGEURL")
    val imageUrl: String
)

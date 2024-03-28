package com.radlance.data.network.pojo.market

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class RawDataDTO(
    @SerializedName("RAW")
    val coinPriceInfoJsonObject: JsonObject
)

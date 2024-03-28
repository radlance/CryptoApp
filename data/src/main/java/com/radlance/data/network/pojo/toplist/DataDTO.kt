package com.radlance.data.network.pojo.toplist

import com.google.gson.annotations.SerializedName

data class DataDTO(
    @SerializedName("CoinInfo")
    val coinInfoDTO: CoinInfoDTO
)

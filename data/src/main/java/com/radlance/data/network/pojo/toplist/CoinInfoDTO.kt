package com.radlance.data.network.pojo.toplist

import com.google.gson.annotations.SerializedName

data class CoinInfoDTO(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("FullName")
    val fullName: String,
    @SerializedName("ImageUrl")
    val imageUrl: String
)

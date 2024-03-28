package com.radlance.data.network.pojo.toplist

import com.google.gson.annotations.SerializedName

data class DataListDTO(
    @SerializedName("Data")
    val dataDTOList: List<DataDTO>
)

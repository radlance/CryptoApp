package com.radlance.data.network.api

import com.radlance.data.network.pojo.market.RawDataDTO
import com.radlance.data.network.pojo.toplist.DataListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApiService {
    @GET("top/totalvolfull")
    suspend fun loadTopList(
        @Query("limit") limit: Int = LIMIT,
        @Query("tsym") toSymbol: String = TO_SYMBOL,
        @Query("ApiKey") apiKey: String = API_KEY
    ): Response<DataListDTO>

    @GET("pricemultifull")
    suspend fun loadFullPriceList(
        @Query("fsyms") fSymbols: String,
        @Query("tsyms") toSymbol: String = TO_SYMBOL,
        @Query("ApiKey") apiKey: String = API_KEY
    ): Response<RawDataDTO>

    companion object {
        const val LIMIT = 15
        private const val TO_SYMBOL = "USD"
        private const val API_KEY =
            "4dea7d67099d08f75a0f95355c32038502f2924f36484aab96b41ccce540f75a"
    }
}
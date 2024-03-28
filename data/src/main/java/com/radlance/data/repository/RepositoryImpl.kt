package com.radlance.data.repository

import com.google.gson.Gson
import com.radlance.data.network.api.CryptoApiService
import com.radlance.data.network.api.CryptoApiService.Companion.LIMIT
import com.radlance.data.network.pojo.market.CoinPriceInfoDTO
import com.radlance.data.network.pojo.market.RawDataDTO
import com.radlance.domain.NetworkResult
import com.radlance.domain.entity.Currency
import com.radlance.domain.entity.Market
import com.radlance.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val cryptoApiService: CryptoApiService) :
    Repository {
    override suspend fun loadCurrencyInfo(): NetworkResult<List<Currency>> {
        return try {
            val response = cryptoApiService.loadTopList()
            if (response.isSuccessful) {
                val dataList = response.body()?.dataDTOList
                val currencyList = mutableListOf<Currency>()
                for (i in 0 until LIMIT) {
                    val coinInfo = dataList!![i].coinInfoDTO
                    with(coinInfo) {
                        currencyList.add(Currency(id.toInt(), name, fullName, imageUrl))
                    }
                }
                NetworkResult.Success(currencyList)
            } else {
                NetworkResult.Error("Failed to load coin info")
            }
        } catch (e: Exception) {
            NetworkResult.Error("Exception: ${e.message}")
        }
    }

    override suspend fun loadMarketInfo(): NetworkResult<List<Market>> {
        return try {
            val response = cryptoApiService.loadFullPriceList()
            val marketInfo = mutableListOf<Market>()
            if (response.isSuccessful) {
                val priceList = getPriceListFromRawData(response.body())
                for (i in 0 until LIMIT) {
                    val priceInfo = priceList[i]
                    with(priceInfo) {
                        marketInfo.add(
                            Market(price, lastUpdate, highDay, lowDay, fromSymbol, toSymbol)
                        )
                    }
                }
                NetworkResult.Success(marketInfo)
            } else {
                NetworkResult.Error("Failed to load market info")
            }
        } catch (e: Exception) {
            NetworkResult.Error("Exception: ${e.message}")
        }
    }

    private fun getPriceListFromRawData(rawDataDTO: RawDataDTO?): List<CoinPriceInfoDTO> {
        val result = mutableListOf<CoinPriceInfoDTO>()
        val jsonObject = rawDataDTO!!.coinPriceInfoJsonObject
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfoDTO::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }
}
package com.radlance.domain.repository

import com.radlance.domain.NetworkResult
import com.radlance.domain.entity.Currency
import com.radlance.domain.entity.Market

interface Repository {
    suspend fun loadCurrencyInfo(): NetworkResult<List<Currency>>
    suspend fun loadMarketInfo(): NetworkResult<List<Market>>
}
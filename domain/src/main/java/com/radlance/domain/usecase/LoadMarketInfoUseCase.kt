package com.radlance.domain.usecase

import com.radlance.domain.NetworkResult
import com.radlance.domain.entity.Market
import com.radlance.domain.repository.Repository
import javax.inject.Inject

class LoadMarketInfoUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): NetworkResult<List<Market>> {
        return repository.loadMarketInfo()
    }
}
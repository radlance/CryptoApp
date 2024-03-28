package com.radlance.domain.usecase

import com.radlance.domain.NetworkResult
import com.radlance.domain.entity.Currency
import com.radlance.domain.repository.Repository
import javax.inject.Inject

class LoadCurrencyInfoUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): NetworkResult<List<Currency>> {
        return repository.loadCurrencyInfo()
    }
}
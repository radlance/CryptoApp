package com.radlance.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radlance.domain.NetworkResult
import com.radlance.domain.entity.Market
import com.radlance.domain.usecase.LoadMarketInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadMarketInfoUseCase: LoadMarketInfoUseCase
) : ViewModel() {
    private val _currencyInfo =
        MutableStateFlow<NetworkResult<List<Market>>>(NetworkResult.Loading())
    val currencyInfo: StateFlow<NetworkResult<List<Market>>> = _currencyInfo.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            while (true) {
                _currencyInfo.value = loadMarketInfoUseCase()
                delay(5000)
            }
        }
    }
}

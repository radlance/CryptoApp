package com.radlance.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radlance.domain.NetworkResult
import com.radlance.domain.entity.Currency
import com.radlance.domain.usecase.LoadCurrencyInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadCurrencyInfoUseCase: LoadCurrencyInfoUseCase
) : ViewModel() {
    private val _currencyInfo =
        MutableStateFlow<NetworkResult<List<Currency>>>(NetworkResult.Loading())
    val currencyInfo: StateFlow<NetworkResult<List<Currency>>> = _currencyInfo.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _currencyInfo.value = NetworkResult.Loading()
            _currencyInfo.value = loadCurrencyInfoUseCase()
        }
    }
}
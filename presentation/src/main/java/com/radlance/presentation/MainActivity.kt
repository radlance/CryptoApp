package com.radlance.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.radlance.domain.NetworkResult
import com.radlance.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvCurrencies: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        collectMarketInfo()
    }

    private fun setupRecyclerView() {
        rvCurrencies = binding.currenciesRv
        rvCurrencies.adapter = CurrencyListAdapter()
    }

    private fun collectMarketInfo() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.currencyInfo.collect {
                    when(it) {
                        is NetworkResult.Success -> setupRecyclerView()
                        is NetworkResult.Error -> Log.e("network", "error")
                        is NetworkResult.Loading -> Log.e("network", "loading")
                    }
                }
            }
        }
    }
}
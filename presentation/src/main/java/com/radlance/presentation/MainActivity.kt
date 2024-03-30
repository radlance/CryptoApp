package com.radlance.presentation

import android.os.Bundle
import android.view.View
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
    private lateinit var currencyListAdapter: CurrencyListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupRecyclerView()
        collectMarketInfo()
    }

    private fun setupRecyclerView() {
        currencyListAdapter = CurrencyListAdapter()
        rvCurrencies = binding.currenciesRv
        rvCurrencies.adapter = currencyListAdapter
    }

    private fun collectMarketInfo() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.currencyInfo.collect { networkResult ->
                    when (networkResult) {
                        is NetworkResult.Success -> {
                            currencyListAdapter.marketInfoList = networkResult.data!!
                                .sortedByDescending { it.lastUpdate }
                            binding.pbLoading.visibility = View.INVISIBLE
                            binding.tvMain.visibility = View.INVISIBLE
                        }

                        is NetworkResult.Error -> {
                            binding.pbLoading.visibility = View.INVISIBLE
                            binding.tvMain.visibility = View.VISIBLE
                        }

                        is NetworkResult.Loading -> binding.pbLoading.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}
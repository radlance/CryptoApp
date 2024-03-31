package com.radlance.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.radlance.domain.NetworkResult
import com.radlance.presentation.databinding.ActivityCryptoDetailsBinding
import com.radlance.presentation.utils.convertTimeStampToTime
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CryptoDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCryptoDetailsBinding
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCryptoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getExtraData()
    }

    private fun getExtraData() {
        val fromSymbols = intent.getStringExtra(EXTRA_FROM_SYMBOLS)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.currencyInfo.collect { networkResult ->
                    when (networkResult) {
                        is NetworkResult.Success -> {
                            val cryptoDetails = networkResult.data!!
                                .filter { it.fromSymbol == fromSymbols }[0]
                            runOnUiThread {
                                with(binding) {
                                    Picasso.get().load(
                                        cryptoDetails.getFullImageUrl()
                                    ).into(ivIcon)
                                    tvLastOffer.text = cryptoDetails.lastMarket
                                    tvUpdate.text = convertTimeStampToTime(cryptoDetails.lastUpdate)
                                    tvMaxPrice.text = cryptoDetails.highDay.toString()
                                    tvMinPrice.text = cryptoDetails.lowDay.toString()
                                    tvCurrentPrice.text = cryptoDetails.price.toString()
                                    tvFromSymbols.text = cryptoDetails.fromSymbol
                                    tvToSymbol.text = cryptoDetails.toSymbol
                                }
                            }
                        }

                        is NetworkResult.Error -> {}

                        is NetworkResult.Loading -> {}
                    }
                }
            }
        }
    }

    companion object {

        private const val EXTRA_FROM_SYMBOLS = "EXTRA_FROM_SYMBOLS"
        fun newInstance(context: Context, fromSymbols: String): Intent {
            val intent = Intent(context, CryptoDetailsActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOLS, fromSymbols)
            return intent
        }
    }
}
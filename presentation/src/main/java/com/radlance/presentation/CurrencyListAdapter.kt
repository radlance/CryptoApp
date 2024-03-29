package com.radlance.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.radlance.domain.entity.Market
import com.radlance.presentation.databinding.ItemCurrencyBinding
import com.squareup.picasso.Picasso

class CurrencyListAdapter : RecyclerView.Adapter<CurrencyListAdapter.CurrencyListViewHolder>() {
    private var marketInfoList = listOf<Market>()
        set(value) {
            val callBack = CurrencyListDiffCallBack(marketInfoList, value)
            val diffResult = DiffUtil.calculateDiff(callBack)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyListViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_currency, parent, false)
        return CurrencyListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return marketInfoList.size
    }

    override fun onBindViewHolder(holder: CurrencyListViewHolder, position: Int) {
        holder.bind(marketInfoList[position])
    }

    class CurrencyListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCurrencyBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(market: Market) {
            with(binding) {
                tvPrice.text = market.price.toString()
                tvLastUpdate.text = market.lastUpdate
                tvSymbols.text = market.fromSymbol + " / " + market.toSymbol
                Picasso.get().load(market.getFullImageUrl()).into(ivCoinIcon)
            }
        }
    }
}
package com.radlance.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.radlance.domain.entity.Market
import com.radlance.presentation.databinding.ItemCurrencyBinding
import com.radlance.presentation.utils.convertTimeStampToTime
import com.squareup.picasso.Picasso

class CurrencyListAdapter : RecyclerView.Adapter<CurrencyListAdapter.CurrencyListViewHolder>() {
    var onItemClickListener: ((Market) -> Unit)? = null
    var marketInfoList = listOf<Market>()
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
        val market = marketInfoList[position]
        holder.bind(market, holder)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(market)
        }
    }

    class CurrencyListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCurrencyBinding.bind(itemView)

        fun bind(market: Market, holder: CurrencyListViewHolder) {
            with(binding) {
                tvPrice.text = market.price.toString()
                tvLastUpdate.text = convertTimeStampToTime(market.lastUpdate)
                tvSymbols.text = buildString {
                    append(market.fromSymbol)
                    append(holder.itemView.context.getString(R.string.slash))
                    append(market.toSymbol)
                }
                Picasso.get().load(market.getFullImageUrl()).into(ivCoinIcon)
            }
        }
    }
}
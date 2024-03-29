package com.radlance.presentation

import androidx.recyclerview.widget.DiffUtil
import com.radlance.domain.entity.Market
import javax.inject.Inject

class CurrencyListDiffCallBack @Inject constructor(
    private val oldList: List<Market>,
    private val newList: List<Market>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].toSymbol == newList[newItemPosition].toSymbol
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}
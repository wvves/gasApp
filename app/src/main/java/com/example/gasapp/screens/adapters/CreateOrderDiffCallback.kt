package com.example.gasapp.screens.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.gasapp.model.stations.entities.Oil

class CreateOrderDiffCallback(
    private val oldList: List<Oil>,
    private val newList: List<Oil>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].oilId == newList[newItemPosition].oilId
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
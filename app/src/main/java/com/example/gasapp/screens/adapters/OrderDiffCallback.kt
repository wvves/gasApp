package com.example.gasapp.screens.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.gasapp.model.stations.entities.Order

class OrderDiffCallback(
    private val oldList: List<Order>,
    private val newList: List<Order>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].orderId == newList[newItemPosition].orderId
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
package com.example.gasapp.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gasapp.R
import com.example.gasapp.databinding.ItemOrderBinding
import com.example.gasapp.model.stations.entities.Order
import java.text.DateFormat

class OrderAdapter() : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private var orders: List<Order> = emptyList()

    class OrderViewHolder(
        val binding: ItemOrderBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderBinding.inflate(inflater, parent, false)
        return OrderViewHolder(binding)
    }

    override fun getItemCount(): Int = orders.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]

        with(holder.binding) {
            stationNameTextView.text = order.station.stationName
            addressTextView.text = order.station.address
            accountNameTextView.text = order.account.username
            val formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(order.createdAt)
            createdAtTextView.text = formatter
            oilTypeTextView.text = order.oil.type
            oilPriceTextView.text = order.oil.price.toString()
            photoImageView.setImageResource(R.drawable.ic_station_24)
        }
    }

    fun renderSettings(orders: List<Order>) {
        val diffResult = DiffUtil.calculateDiff(OrderDiffCallback(this.orders, orders))
        this.orders = orders
        diffResult.dispatchUpdatesTo(this)
    }
}
package com.example.gasapp.screens.tabs.order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gasapp.R
import com.example.gasapp.Repositories
import com.example.gasapp.databinding.FragmentOrdersBinding
import com.example.gasapp.screens.adapters.OrderAdapter
import com.example.gasapp.utils.viewModelCreator

class OrdersFragment: Fragment(R.layout.fragment_orders) {

    private lateinit var binding: FragmentOrdersBinding

    private val viewModel by viewModelCreator { OrdersViewModel(Repositories.ordersRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrdersBinding.bind(view)

        val adapter = setupList()

        viewModel.orders.observe(viewLifecycleOwner) { adapter.renderSettings(it) }
    }

    private fun setupList(): OrderAdapter {
        binding.orderList.layoutManager = LinearLayoutManager(requireContext())
        val adapter = OrderAdapter()
        binding.orderList.adapter = adapter
        return adapter
    }
}
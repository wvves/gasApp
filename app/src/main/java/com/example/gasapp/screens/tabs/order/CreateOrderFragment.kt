package com.example.gasapp.screens.tabs.order

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gasapp.R
import com.example.gasapp.Repositories
import com.example.gasapp.databinding.FragmentCreateOrderBinding
import com.example.gasapp.model.stations.entities.OrderDbEntity
import com.example.gasapp.screens.adapters.OilTypeAdapter
import com.example.gasapp.utils.InputFilterMinMax
import com.example.gasapp.utils.observeEvent
import com.example.gasapp.utils.viewModelCreator
import kotlin.math.roundToInt

class CreateOrderFragment(): Fragment(R.layout.fragment_create_order) {

    private lateinit var binding: FragmentCreateOrderBinding

    private val viewModel by viewModelCreator { CreateOrderViewModel(
        Repositories.stationsRepository,
        Repositories.ordersRepository,
        Repositories.accountsRepository
    ) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateOrderBinding.bind(view)


        val adapter = setupList()
        viewModel.oils.observe(viewLifecycleOwner) { adapter.renderSettings(it) }

        binding.payButton.setOnClickListener { createOrder() }
        binding.capacityTextView.setOnClickListener { changeTextViewToCapacity() }

        var price: Long = 0

        viewModel.orderOil.observe(viewLifecycleOwner) {
            price = it.price
            binding.allSumTextView.text = it.price.toString()
        }


        observeShowSuccessTakeOilTypeMessageEvent()
        binding.PriceOrCapacityEditInput.addTextChangedListener {

            println(price)
            if (binding.allSumTextView.isSelected) {

                val number = it.toString().toDoubleOrNull() ?: 0.0
                println("number Double: $price")
                val capacityOil = number / price.toDouble()
                binding.allSumTextView.text = number.toString()
                binding.allCapacityTextView.text = capacityOil.toString()

                println("number Double: $capacityOil")
            } else if (binding.allCapacityTextView.isSelected) {
                println("2")
                val number = it.toString().toDoubleOrNull() ?: 0.0
                val sumOil = number * price
                binding.allCapacityTextView.text = number.toString()
                binding.allSumTextView.text = sumOil.toString()
                println("number Double: $price")
                println("number Double: $sumOil")
            }
        }
        binding.sumTextView.setOnClickListener { changeTextViewToPrice() }
    }

    private fun setupList(): OilTypeAdapter {
        binding.oilsTypeList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapter = OilTypeAdapter(viewModel)
        binding.oilsTypeList.adapter = adapter
        return adapter
    }

    private fun observeShowSuccessTakeOilTypeMessageEvent() = viewModel.showToastEvent.observeEvent(viewLifecycleOwner) {
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }

    private fun createOrder() {
        println("@@@")
        println(OrderDbEntity(
            orderId = 0,
            id = viewModel.orderAccount.value?.id!!,
            stationId = viewModel.orderStation.value?.stationId!!,
            oilId = viewModel.orderOil.value?.oilId!!,
            createdAt = System.currentTimeMillis()
        ))
    }

    private fun changeTextViewToCapacity() {
        binding.allCapacityTextView.isSelected = true
        binding.allSumTextView.isSelected = false
        binding.allCapacityTextView.text = "${1}"

        binding.capacityOrSumTextView.setText(R.string.enter_capacity)
        binding.action5or1000.setText(R.string.action_5)
        binding.action10or2000.setText(R.string.action_10)
        binding.action20or3000.setText(R.string.action_20)

        binding.PriceOrCapacityEditInput.filters = arrayOf(InputFilterMinMax(1,999))
        binding.PriceOrCapacityEditInput.setText(binding.allCapacityTextView.text)
    }

    private fun changeTextViewToPrice() = binding.apply {

        allSumTextView.isSelected = true
        allCapacityTextView.isSelected = false
        allSumTextView.text = viewModel.orderOil.value?.price!!.toString()
        capacityOrSumTextView.setText(R.string.enter_price)
        action5or1000.setText(R.string.action_1000)
        action10or2000.setText(R.string.action_2000)
        action20or3000.setText(R.string.action_3000)

        val min = viewModel.orderOil.value?.price!!.toInt()
        val max = 999 * viewModel.orderOil.value?.price!!.toInt()
        PriceOrCapacityEditInput.filters = arrayOf(InputFilterMinMax(min, max))
        PriceOrCapacityEditInput.setText(binding.allSumTextView.text)
    }
}
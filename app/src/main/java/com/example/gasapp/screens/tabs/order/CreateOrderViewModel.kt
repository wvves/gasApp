package com.example.gasapp.screens.tabs.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gasapp.R
import com.example.gasapp.model.accounts.entites.Account
import com.example.gasapp.model.accounts.room.AccountsRepository
import com.example.gasapp.model.stations.entities.Oil
import com.example.gasapp.model.stations.entities.StationDbEntity
import com.example.gasapp.model.stations.room.OrdersRepository
import com.example.gasapp.model.stations.room.StationsRepository
import com.example.gasapp.screens.adapters.OilTypeAdapter
import com.example.gasapp.utils.MutableLiveEvent
import com.example.gasapp.utils.publishEvent
import com.example.gasapp.utils.share
import kotlinx.coroutines.launch

class CreateOrderViewModel(
    private val stationsRepository: StationsRepository,
    private val ordersRepository: OrdersRepository,
    private val accountsRepository: AccountsRepository
): ViewModel(), OilTypeAdapter.Listener
{
    private val _oils = MutableLiveData<List<Oil>>()
    val oils = _oils.share()

    private val _showToastEvent = MutableLiveEvent<Int>()
    val showToastEvent = _showToastEvent.share()

    private val _orderOil = MutableLiveData<Oil>()
    val orderOil = _orderOil.share()

    private val _orderStation = MutableLiveData<StationDbEntity>()
    val orderStation = _orderStation.share()

    private val _orderAccount = MutableLiveData<Account>()
    val orderAccount = _orderAccount.share()


    init {
        viewModelScope.launch {
            stationsRepository.getOilsById(2).collect {entities ->
                entities.map {
                    _oils.value = it.oils
                    _orderStation.value = it.station
                }
            }
        }

        viewModelScope.launch {
            accountsRepository.getAccount().collect {
                _orderAccount.value = it
            }
        }
    }

    fun addOrder() {
//        viewModelScope.launch {
//            ordersRepository.addOrder(orderDbEntity)
//        }

    }

    override fun enableOrder(oil: Oil) {
        _orderOil.value = oil
        showSuccessSignUpMessage()
    }

    private fun showSuccessSignUpMessage() = _showToastEvent.publishEvent(R.string.take_oil_type)
}
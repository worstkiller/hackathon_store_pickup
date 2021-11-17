package com.falabella.storepickup.orderlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falabella.storepickup.firebase.StoreFirebaseManager
import com.falabella.storepickup.model.StoreAppointmentModel

class OrderListViewModel : ViewModel() {

    private val orderListRepository = OrderListRepository()

    var firebaseManager = StoreFirebaseManager()

    var isCompleted = false

    val orderList = MutableLiveData<List<StoreAppointmentModel>>()

    fun getAppointments(isCompleted: Boolean) {
        firebaseManager.getAllAppointments(isCompleted) {
            val list = mutableListOf<StoreAppointmentModel>()
            list.addAll(it)
            this.isCompleted = isCompleted
            orderList.value = orderListRepository.getFormattedList(list, isCompleted)
        }
    }
}
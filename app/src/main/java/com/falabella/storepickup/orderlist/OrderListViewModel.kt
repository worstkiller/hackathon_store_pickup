package com.falabella.storepickup.orderlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falabella.storepickup.firebase.StoreFirebaseManager
import com.falabella.storepickup.model.StoreAppointmentModel

class OrderListViewModel : ViewModel() {

    private val orderListRepository = OrderListRepository()

    private var firebaseManager = StoreFirebaseManager()

    private var isCompleted = false
    var nearestAppointmentPosition = 0

    val orderList = MutableLiveData<List<StoreAppointmentModel>>()

    fun getAppointments(isCompleted: Boolean) {
        firebaseManager.getAllAppointments(isCompleted) {
            val list = mutableListOf<StoreAppointmentModel>()
            list.addAll(it)
            this.isCompleted = isCompleted
            nearestAppointmentPosition = orderListRepository.getNearestAppointment(list)
            orderList.value = orderListRepository.getFormattedList(list, isCompleted)
        }
    }
}
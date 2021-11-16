package com.falabella.storepickup.orderlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falabella.storepickup.firebase.StoreFirebaseManager
import com.falabella.storepickup.model.StoreAppointmentModel

class OrderListViewModel : ViewModel() {

    private val orderListRepository = OrderListRepository()

    var firebaseManager = StoreFirebaseManager()

    var isCompleted = false

    var orderList = MutableLiveData<List<StoreAppointmentModel>>()

    private fun getAppointments() {
        firebaseManager.getAllAppointments { list ->
            orderList.value = if(isCompleted) {
                orderListRepository.getCompletedList(list)
            } else {
                orderListRepository.getUpcomingList(list)
            }
        }
    }

    fun setIsCompleted(isCompleted: Boolean) {
        this.isCompleted = isCompleted
        getAppointments()
    }
}
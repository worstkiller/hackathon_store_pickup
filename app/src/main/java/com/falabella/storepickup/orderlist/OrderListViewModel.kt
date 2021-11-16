package com.falabella.storepickup.orderlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.falabella.storepickup.model.StoreAppointmentModel

class PageViewModel : ViewModel() {

    private val orderListRepository = OrderListRepository()

    val isCompleted = MutableLiveData<Boolean>()
    val orderList: LiveData<List<StoreAppointmentModel>> = Transformations.map(isCompleted) {
        if(isCompleted.value == true) {
            orderListRepository.getCompletedList()
        } else {
            orderListRepository.getUpcomingList()
        }
    }

    fun setIsCompleted(isCompleted: Boolean) {
        this.isCompleted.value = isCompleted
    }
}
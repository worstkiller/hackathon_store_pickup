package com.falabella.storepickup.orderlist

import com.falabella.storepickup.model.StoreAppointmentModel

class OrderListRepository {

    fun getUpcomingList(list: List<StoreAppointmentModel>) = with(list) {
        forEach { it.updateTimeValues() }
        filter { it.completed.not() }
        sortedBy { it.startTime }
    }

    fun getCompletedList(list: List<StoreAppointmentModel>) = with(list) {
        forEach { it.updateTimeValues() }
        filter { it.completed }
        sortedByDescending { it.startTime }
    }
}

package com.falabella.storepickup.orderlist

import com.falabella.storepickup.model.StoreAppointmentModel

class OrderListRepository {

    fun getFormattedList(
        list: MutableList<StoreAppointmentModel>,
        isCompleted: Boolean
    ) = with(list) {
        forEach { it.updateTimeValues() }

        if (isCompleted) {
            getCompletedList(list)
        } else {
            getUpcomingList(list)
        }
    }

    private fun getUpcomingList(list: MutableList<StoreAppointmentModel>) =
        with(list) { sortedBy { it.startTime }.filter { it.completed.not() } }

    private fun getCompletedList(list: MutableList<StoreAppointmentModel>) =
        with(list) { sortedByDescending { it.startTime }.filter { it.completed } }

}

package com.falabella.storepickup.model

data class StoreAppointmentModel(
    val appointmentId: String,
    val storeId: String,
    val startTime: String,
    val endTime: String,
    val orderNo: String,
    val customerId: String,
    val range: String,
    val orderPrice: String,
    val isCompleted: Boolean,
    val images: List<String>,
    val slotId: String,
    val customerName: String,
    val documentNo: String
)

package com.falabella.storepickup.model

/**
 * model for store configurations
 */
data class StoreConfigurationModel(
    val storeId: String,
    val storeName: String,
    val directions: String,
    val lattitude: String,
    val longitude: String,
    val storeStartTime: String,
    val storeEndTime: String,
    val customersByDefault: Int,
    val startDayOfWeek: String,
    val endDayOfWeek: String,
    val storeSlots: List<StoreSlots>
)

data class StoreSlots(
    val id: Int,
    val range: String,
    val starDate: String,
    val endDate: String,
    val enabled: Boolean,
    val noOfCustomers: Int,
)
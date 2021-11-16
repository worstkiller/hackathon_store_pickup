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
    var date: Long,
    val storeStartTime: Int,
    val storeEndTime: Int,
    var customersByDefault: Int,
    var slotRange: Int,
    var storeSlots: List<StoreSlots>
)

data class StoreSlots(
    val id: Long,
    val range: String,
    val starDate: String,
    val endDate: String,
    val enabled: Boolean,
    val noOfCustomers: Int,
)
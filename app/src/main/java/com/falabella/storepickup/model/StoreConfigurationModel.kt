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
    var date: Long = 0,
    val storeStartTime: Int,
    val storeEndTime: Int,
    var customersByDefault: Int,
    var slotRange: Int,
    var storeSlots: List<StoreSlots>
) {

    constructor() : this("", "", "", "", "", 0, 0, 0, 0, 0, emptyList())

    override fun toString(): String {
        return storeName
    }

}

data class StoreSlots(
    val id: Long,
    val range: String,
    val starDate: String,
    val endDate: String,
    val enabled: Boolean,
    val noOfCustomers: Int,
) {
    constructor() : this(0, "", "", "", false, 0)
}
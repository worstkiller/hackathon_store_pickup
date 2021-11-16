package com.falabella.storepickup.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.falabella.storepickup.utils.OrderConstants
import com.falabella.storepickup.utils.UiUtils

data class StoreAppointmentModel(
    val appointmentId: String? = null,
    val startTime: Long = 0,
    val customerName: String? = null,
    val storeId: String? = null,
    val endTime: Long = 0,
    val orderNo: String? = null,
    val customerId: String? = null,
    val range: String? = null,
    val orderPrice: String? = null,
    val slotId: String? = null,
    val documentNo: String? = null,
    val products: List<Product>? = null,
    val isCompleted: Boolean = false,

    //vars for local use/ UI
    var startDate: String? = null,
    var startTimeSlot: String? = null,
    var endDate: String? = null,
    var endTimeSlot: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Product),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    )

    fun updateTimeValues() {
        startDate = UiUtils.getSimpleDateTimeFormat(startTime)
        startTimeSlot = UiUtils.getSimpleDateTimeFormat(startTime, OrderConstants.SIMPLE_TIME_FORMAT)
        endDate = UiUtils.getSimpleDateTimeFormat(endTime)
        endTimeSlot = UiUtils.getSimpleDateTimeFormat(endTime, OrderConstants.SIMPLE_TIME_FORMAT)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(appointmentId)
        parcel.writeLong(startTime)
        parcel.writeString(customerName)
        parcel.writeString(storeId)
        parcel.writeLong(endTime)
        parcel.writeString(orderNo)
        parcel.writeString(customerId)
        parcel.writeString(range)
        parcel.writeString(orderPrice)
        parcel.writeString(slotId)
        parcel.writeString(documentNo)
        parcel.writeTypedList(products)
        parcel.writeByte(if (isCompleted) 1 else 0)
        parcel.writeString(startDate)
        parcel.writeString(startTimeSlot)
        parcel.writeString(endDate)
        parcel.writeString(endTimeSlot)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<StoreAppointmentModel> {
        override fun createFromParcel(parcel: Parcel): StoreAppointmentModel {
            return StoreAppointmentModel(parcel)
        }

        override fun newArray(size: Int): Array<StoreAppointmentModel?> {
            return arrayOfNulls(size)
        }
    }


}

data class Product(val name: String?, val image: String?, val price: Float, val quantity: Int) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeFloat(price)
        parcel.writeInt(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }


}

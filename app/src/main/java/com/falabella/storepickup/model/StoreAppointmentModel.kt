package com.falabella.storepickup.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class StoreAppointmentModel(
    val appointmentId: String? = null,
    val startTime: String? = null,
    val customerName: String? = null,
    val storeId: String? = null,
    val endTime: String? = null,
    val orderNo: String? = null,
    val customerId: String? = null,
    val range: String? = null,
    val orderPrice: String? = null,
    val slotId: String? = null,
    val documentNo: String? = null,
    val images: List<String>? = null,
    val isCompleted: Boolean = false
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(appointmentId)
        parcel.writeString(startTime)
        parcel.writeString(customerName)
        parcel.writeString(storeId)
        parcel.writeString(endTime)
        parcel.writeString(orderNo)
        parcel.writeString(customerId)
        parcel.writeString(range)
        parcel.writeString(orderPrice)
        parcel.writeString(slotId)
        parcel.writeString(documentNo)
        parcel.writeStringList(images)
        parcel.writeByte(if (isCompleted) 1 else 0)
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

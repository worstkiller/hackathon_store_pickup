package com.falabella.storepickup.barcode

import androidx.lifecycle.ViewModel
import com.falabella.storepickup.firebase.StoreFirebaseManager
import com.falabella.storepickup.model.StoreAppointmentModel

class BarcodeViewModel : ViewModel() {

    var firebaseManager = StoreFirebaseManager()

    fun updateAppointmentStatus(storeAppointmentModel: StoreAppointmentModel, callback: (Boolean) -> Unit) {
        firebaseManager.addOrUpdateAppointment(storeAppointmentModel, callback)
    }
}
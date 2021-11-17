package com.falabella.storepickup.firebase

import com.falabella.storepickup.model.StoreAppointmentModel
import com.falabella.storepickup.model.StoreConfigurationModel
import com.falabella.storepickup.utils.OrderConstants
import com.google.firebase.firestore.FirebaseFirestore

class StoreFirebaseManager {

    var db = FirebaseFirestore.getInstance()

    companion object {
        const val NODE_STORE = "STORES"
        const val NODE_APPOINTMENTS = "APPOINTMENTS"
    }

    fun updateStore(storeConfigurationModel: StoreConfigurationModel, callback: (Boolean) -> Unit) {
        db.collection(NODE_STORE).document(storeConfigurationModel.storeId)
            .set(storeConfigurationModel)
            .addOnSuccessListener { documentReference ->
                callback(true)
            }
            .addOnFailureListener { e ->
                callback(false)
            }
    }

    fun getAllStores(callback: (List<StoreConfigurationModel>) -> Unit) {
        db.collection(NODE_STORE).get().addOnSuccessListener { result ->
            val dat = result?.toObjects(StoreConfigurationModel::class.java)
            callback(dat ?: emptyList())
        }.addOnFailureListener { exception ->
            callback(emptyList())
        }
    }

    fun addOrUpdateAppointment(
        storeAppointmentModel: StoreAppointmentModel, callback: (Boolean) ->
        Unit
    ) {
        db.collection(NODE_APPOINTMENTS)
            .document(storeAppointmentModel.appointmentId.orEmpty())
            .set(storeAppointmentModel)
            .addOnSuccessListener { documentReference ->
                callback(true)
            }
            .addOnFailureListener { e ->
                callback(false)
            }
    }

    fun getAllAppointments(isCompleted: Boolean = false, callback: (List<StoreAppointmentModel>) -> Unit) {
        db.collection(NODE_APPOINTMENTS)
            .whereEqualTo(OrderConstants.completed, isCompleted)
            .get()
            .addOnSuccessListener { result ->
            val dat = result?.toObjects(StoreAppointmentModel::class.java)
            callback(dat ?: emptyList())
        }.addOnFailureListener { exception ->
            callback(emptyList())
        }
    }

    fun observeAppointmentsChanges(isStart: Boolean, callback: (StoreAppointmentModel?) -> Unit) {
        var isStartLocal = isStart
        db.collection(NODE_APPOINTMENTS).addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val dat = snapshot.toObjects(StoreAppointmentModel::class.java).filter {
                    it.completed.not()
                }.sortedBy {
                    it.startTime
                }
                if (isStartLocal.not()) callback(dat.firstOrNull())
                isStartLocal = false
            }
        }
    }

}
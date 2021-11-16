package com.falabella.storepickup.firebase

import com.falabella.storepickup.model.StoreConfigurationModel
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

}
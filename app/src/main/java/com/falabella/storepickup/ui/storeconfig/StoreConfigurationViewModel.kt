package com.falabella.storepickup.ui.storeconfig

import androidx.lifecycle.ViewModel
import com.falabella.storepickup.firebase.StoreFirebaseManager
import com.falabella.storepickup.model.StoreConfigurationModel


class StoreConfigurationViewModel : ViewModel() {

    var selectedStore: StoreConfigurationModel? = null

    var firebaseManager = StoreFirebaseManager()

    fun getDummyData(): StoreConfigurationModel {
        return StoreConfigurationModel(
            "101",
            "Falabella Lyon",
            "Nueva de Lyon 064, Providencia, Región Metropolitana",
            "-33.421147", "-70.610579", 0,
            9,
            23,
            3, 30, arrayListOf(),
        )
    }

    fun updateData(storeConfigurationModel: StoreConfigurationModel, callback: (Boolean) -> Unit) {
        firebaseManager.updateStore(storeConfigurationModel, callback)
    }

}
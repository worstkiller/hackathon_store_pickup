package com.falabella.storepickup.ui.storeconfig

import androidx.lifecycle.ViewModel
import com.falabella.storepickup.model.StoreConfigurationModel

class StoreConfigurationViewModel : ViewModel() {

    var selectedStore: StoreConfigurationModel? = null

    fun getDummyData(): StoreConfigurationModel {
        return StoreConfigurationModel(
            "101",
            "Falabella Lyon",
            "Nueva de Lyon 064, Providencia, Región Metropolitana",
            "-33.421147", "-70.610579", 1637012019,
            "1637012019",
            "1637019219",
            3, "30", arrayListOf(),
        )
    }

}
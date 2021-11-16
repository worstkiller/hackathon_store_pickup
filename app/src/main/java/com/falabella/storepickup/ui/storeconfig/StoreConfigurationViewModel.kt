package com.falabella.storepickup.ui.storeconfig

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falabella.storepickup.firebase.StoreFirebaseManager
import com.falabella.storepickup.model.StoreConfigurationModel


class StoreConfigurationViewModel : ViewModel() {

    var selectedStore: StoreConfigurationModel? = null

    var firebaseManager = StoreFirebaseManager()

    var listOfStores = MutableLiveData<List<StoreConfigurationModel>>()

    var selectedStoreConfig = MutableLiveData<StoreConfigurationModel>()

    fun updateData(storeConfigurationModel: StoreConfigurationModel, callback: (Boolean) -> Unit) {
        firebaseManager.updateStore(storeConfigurationModel, callback)
    }

    fun getAllStores() {
        firebaseManager.getAllStores { stores ->
            listOfStores.value = stores
            selectedStoreConfig.value = stores.firstOrNull()
        }
    }

    fun updateSelectedStore(which: Int) {
        selectedStoreConfig.value = listOfStores.value?.get(which)
    }

}
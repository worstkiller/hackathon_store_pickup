package com.falabella.storepickup.ui.storeconfig

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.falabella.storepickup.databinding.ActivityStoreConfigurationBinding

/**
 * to show the store configuration ui
 */
class StoreConfigurationActivity : AppCompatActivity() {

    lateinit var binding: ActivityStoreConfigurationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreConfigurationBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setUpStoreConfig()
    }

    private fun setUpStoreConfig() {

    }

}
package com.falabella.storepickup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.falabella.storepickup.databinding.ActivityMainBinding
import com.falabella.storepickup.ui.storeconfig.StoreConfigurationActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setUpMainView()
    }

    private fun setUpMainView() {
        //click listener
        binding.ivSettings.setOnClickListener {
            val intent = Intent(this@MainActivity, StoreConfigurationActivity::class.java)
            startActivity(intent)
        }
    }
}
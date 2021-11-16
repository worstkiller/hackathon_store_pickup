package com.falabella.storepickup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.falabella.storepickup.databinding.ActivityMainBinding
import com.falabella.storepickup.model.StoreAppointmentModel
import com.falabella.storepickup.ui.storeconfig.StoreConfigurationActivity
import com.falabella.storepickup.utils.OrderConstants.BundleKeys

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // todo: sruthi: use this data from intent
        val storeAppointmentModel = intent?.extras?.getParcelable<StoreAppointmentModel>(BundleKeys.KEY_ORDER_ITEM)
        val orderId = intent?.extras?.getString(BundleKeys.KEY_ORDER_ID)
        binding.orderIdTv.text = orderId
        storeAppointmentModel?.apply {
            binding.customerNameTv.text = customerName
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

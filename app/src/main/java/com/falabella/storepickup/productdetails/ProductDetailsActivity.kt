package com.falabella.storepickup.productdetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.falabella.storepickup.R
import com.falabella.storepickup.adapter.ProductListAdapter
import com.falabella.storepickup.databinding.ActivityProductDetailsBinding
import com.falabella.storepickup.model.Product
import com.ncorti.slidetoact.SlideToActView
import com.falabella.storepickup.barcode.BarcodeActivity
import com.falabella.storepickup.barcode.ScanAlertDialog

class ProductDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductDetailsBinding

    private lateinit var adapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setUpMainView()
        setUpClickListeners()
    }

    private fun setUpMainView() {
        val dummyData = listOf(Product("https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg", 10.0F, 2))
//        val dummyData = StoreAppointmentModel(
//            "1234", "12", "10:00", "18:00", "12345", "ABC123", "2", "$10", false,
//            listOf(
//                Product("Noodles", "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg", "$10"),
//                Product("Soap", "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg", "$5"),
//            Product("Noodles", "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg", "$10"),
//            Product("Soap", "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg", "$5")),
//            "345", "Shruti", "345")
        binding.rvProductsList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = ProductListAdapter(dummyData)
        binding.rvProductsList.adapter = adapter
//        binding.customerName.text = dummyData.customerName
//        binding.customerId.text = dummyData.customerId
//        binding.tvDeliveryStatus.text = if (dummyData.isCompleted) "Terminado" else "Pendiente"
//        binding.tvDeliveryStatus.setTextColor(if (dummyData.isCompleted) resources.getColor(R.color.green) else resources.getColor(R.color.red))
//        binding.btnMarkAsDelivered.visibility = if (dummyData.isCompleted) View.GONE else View.VISIBLE
//        binding.tvOrderNumber.text = dummyData.orderNo
    }

    override fun onResume() {
        super.onResume()
        binding.btnMarkAsDelivered.resetSlider()
    }

    private fun setUpClickListeners() {
        binding.btnMarkAsDelivered.onSlideCompleteListener = object :
            SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                val intent = Intent(this@ProductDetailsActivity, BarcodeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}


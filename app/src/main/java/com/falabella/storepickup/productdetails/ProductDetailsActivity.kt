package com.falabella.storepickup.productdetails

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.falabella.storepickup.R
import com.falabella.storepickup.adapter.ProductListAdapter
import com.falabella.storepickup.barcode.BarcodeActivity
import com.falabella.storepickup.databinding.ActivityProductDetailsBinding
import com.falabella.storepickup.model.StoreAppointmentModel
import com.falabella.storepickup.utils.OrderConstants.BundleKeys.KEY_ORDER_ITEM
import com.falabella.storepickup.utils.UiUtils.isVisible
import com.ncorti.slidetoact.SlideToActView
import java.text.DateFormat

class ProductDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductDetailsBinding

    private lateinit var adapter: ProductListAdapter

    var bundleData: StoreAppointmentModel? = StoreAppointmentModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setUpMainView()
        setUpClickListeners()
    }

    @SuppressLint("SetTextI18n")
    private fun setUpMainView() {
        bundleData = intent.getParcelableExtra<StoreAppointmentModel>(KEY_ORDER_ITEM)
        //recyclerview
        binding.rvProductsList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = ProductListAdapter(this, bundleData?.products ?: emptyList())
        binding.rvProductsList.adapter = adapter
        //other ui elemens
        binding.customerInfoTitle.text = "#${bundleData?.orderNo}"
        binding.tvDate.text = DateFormat.getDateInstance().format(bundleData?.startTime)
        binding.tvOrderTotal.text = bundleData?.orderPrice
        binding.customerIdLabel.text = "${binding.customerIdLabel.text}: ${bundleData?.documentNo}"
        binding.customerNameLabel.text =
            "${binding.customerNameLabel.text}: ${bundleData?.customerName}"
        binding.tvTimeSlot.text = bundleData?.range.toString()
        bundleData?.let { binding.btnMarkAsDelivered.isVisible(!it.isCompleted) }
        bundleData?.let { binding.tvDeliveryComplete.isVisible(it.isCompleted) }
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
                intent.putExtras(Bundle().apply {
                    putParcelable(KEY_ORDER_ITEM, bundleData)
                })
                startActivity(intent)
            }
        }

        binding.ivBackButton.setOnClickListener {
            finish()
        }
    }

}


package com.falabella.storepickup.barcode

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.barcodescanner.ScanResult
import com.barcodescanner.ScanResultListener
import com.barcodescanner.ScannerFragment
import com.falabella.storepickup.R
import com.falabella.storepickup.model.StoreAppointmentModel
import com.falabella.storepickup.utils.OrderConstants
import com.falabella.storepickup.utils.ProgressDialog

class BarcodeActivity : AppCompatActivity(), ScanResultListener {

    lateinit var viewModel: BarcodeViewModel
    lateinit var progress: ProgressDialog
    var bundleData: StoreAppointmentModel? = StoreAppointmentModel()

    override fun onScanSuccess(scanResult: ScanResult) {
        if (bundleData?.appointmentId == scanResult.result) {
            updateFirebaseDB()
        } else {
            showDeliveryStatusDialog(false)
        }
    }

    override fun onScanFailed(reason: String) {
        Toast.makeText(this, "" + reason, Toast.LENGTH_SHORT).show()
        Toast.makeText(
            this@BarcodeActivity,
            getString(R.string.please_scan_again),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.barcode_layout)
        viewModel = defaultViewModelProviderFactory.create(BarcodeViewModel::class.java)
        val closeBarcodeImage = findViewById<ImageView>(R.id.close_barcode_img)
        closeBarcodeImage.setOnClickListener {
            finish()
        }
        progress = ProgressDialog(this)
        bundleData =
            intent.getParcelableExtra<StoreAppointmentModel>(OrderConstants.BundleKeys.KEY_ORDER_ITEM)
        addFragment()
    }

    fun addFragment() {
        val newFragment = ScannerFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.scannerfragment, newFragment).commit()
    }

    private fun showDeliveryStatusDialog(isDeliveryComplete: Boolean) {
        val scanAlertDialog: ScanAlertDialog = ScanAlertDialog.newInstance(isDeliveryComplete)
        scanAlertDialog.show(supportFragmentManager, scanAlertDialog::class.java.simpleName)
    }

    private fun updateFirebaseDB() {
        progress.show()
        bundleData?.let {
            it.completed = true
            viewModel.updateAppointmentStatus(it) { success ->
                progress.hide()
                if (success) {
                    showDeliveryStatusDialog(true)
                } else {
                    showDeliveryStatusDialog(false)
                }
            }
        }
    }
}
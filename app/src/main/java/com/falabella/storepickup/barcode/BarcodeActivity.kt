package com.falabella.storepickup.barcode

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.barcodescanner.ScanResult
import com.barcodescanner.ScanResultListener
import com.barcodescanner.ScannerFragment
import com.falabella.storepickup.R

class BarcodeActivity : AppCompatActivity(), ScanResultListener {

    override fun onScanSuccess(scanResult: ScanResult) {
        finish()
        val bundle = Bundle()
        bundle.putString("productId", scanResult.result)
        bundle.putBoolean("isFromScanner", true)
        updateFirebaseDB()
        showDeliveryStatusDialog(true)
        Toast.makeText(this, "Scan successful", Toast.LENGTH_LONG).show()
    }

    override fun onScanFailed(reason: String) {
        Toast.makeText(this, "" + reason, Toast.LENGTH_SHORT).show()
        showDeliveryStatusDialog(false)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.barcode_layout)
        val closeBarcodeImage = findViewById<ImageView>(R.id.close_barcode_img)
        closeBarcodeImage.setOnClickListener {
            finish()
        }
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

    }
}
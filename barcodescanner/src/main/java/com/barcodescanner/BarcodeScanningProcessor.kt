package com.barcodescanner

import android.graphics.Bitmap
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import java.io.IOException

/**
 * Created by Venkatesh Murthy on 04/02/19.
 */
class BarcodeScanningProcessor : VisionProcessorBase<List<FirebaseVisionBarcode>>() {


    private val detector: FirebaseVisionBarcodeDetector

    init {
        // Note that if you know which format of barcode your app is dealing with, detection will be
        // faster to specify the supported barcode formats one by one, e.g.
        val firebaseVisionBarcodeDetectorOptions = FirebaseVisionBarcodeDetectorOptions.Builder()
            .setBarcodeFormats(
                FirebaseVisionBarcode.FORMAT_EAN_8,
                FirebaseVisionBarcode.FORMAT_EAN_13,
                FirebaseVisionBarcode.FORMAT_UPC_A,
                FirebaseVisionBarcode.FORMAT_UPC_E,
                FirebaseVisionBarcode.FORMAT_QR_CODE,
                FirebaseVisionBarcode.FORMAT_CODE_128
            )
            .build()
        detector = FirebaseVision.getInstance().getVisionBarcodeDetector(firebaseVisionBarcodeDetectorOptions)
    }

    override fun stop() {
        try {
            detector.close()
        } catch (e: IOException) {
            Log.e(TAG, "Exception thrown while trying to close Barcode Detector: $e")
        }

    }

    override fun detectInImage(image: FirebaseVisionImage): Task<List<FirebaseVisionBarcode>> {
        return detector.detectInImage(image)
    }

    override fun onSuccess(
        originalCameraImage: Bitmap?,
        barcodes: List<FirebaseVisionBarcode>,
        frameMetadata: FrameMetadata,
        scanResultListener: ScanResultListener
    ) {
        if (barcodes != null && barcodes.size > ZERO_VALUE) {
            val scanResult = ScanResult()
            scanResult.formatType = barcodes[ZERO_VALUE].format
            scanResult.result = barcodes[ZERO_VALUE].displayValue!!
            scanResultListener.onScanSuccess(scanResult)
            stop()
        }
    }

    override fun onFailure(e: Exception, scanResultListener: ScanResultListener) {
        Log.e(TAG, "Barcode detection failed $e")
        e.message?.let { scanResultListener.onScanFailed(it) }
    }

    companion object {

        private val TAG = BarcodeScanningProcessor::class.java.simpleName
    }
}
package com.barcodescanner

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.scanner_view.*
import java.io.IOException

/**
 * Created by Venkatesh Murthy on 04/02/19.
 */

const val REQUESTCODE = 100

class ScannerFragment : Fragment(), ScanResultListener {
    private var cameraSource: CameraSource? = null
    private var isScanning: Boolean = false

    var listener: ScanResultListener? = null

    override fun onScanSuccess(scanResult: ScanResult) {
        listener?.onScanSuccess(scanResult)
        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()

        listener = null
    }

    override fun onScanFailed(reason: String) {
        activity?.let {
            Toast.makeText(it, "" + reason, Toast.LENGTH_SHORT).show()
        }
        listener?.onScanFailed(reason)

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ScanResultListener) {
            listener = context
        } else {
            //throw RuntimeException(context!!.toString() + " must implement FragmentEvent")
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.scanner_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createCameraSource()

        if (context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA) }
            == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUESTCODE)
        } else {
            startCameraSource()
        }
    }

    @Suppress("unused", "UNUSED_PARAMETER")
    fun processBarCode(rawResult: ScanResult) {

    }

    private fun createCameraSource() {
        // If there's no existing cameraSource, create one.
        if (cameraSource == null) {
            cameraSource = context?.let { CameraSource(it, this) }
        }
        cameraSource!!.setFacing(CameraSource.CAMERA_FACING_BACK)
        cameraSource!!.setMachineLearningFrameProcessor(BarcodeScanningProcessor())

    }

    /**
     * Starts or restarts the camera source, if it exists. If the camera source doesn't exist yet
     * (e.g., because onResume was called before the camera source was created), this will be called
     * again when the camera source is created.
     */
    private fun startCameraSource() {
        if (cameraSource != null && context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA) }
            == PackageManager.PERMISSION_GRANTED) {
            try {
                preview.run {
                    preview.start(cameraSource)
                }
                isScanning = true
            } catch (e: IOException) {
                cameraSource!!.release()
                cameraSource = null
            }

        }
    }

    override fun onPause() {
        super.onPause()
        if (preview.isShown) {
            preview.stop()

        }
    }

    override fun onResume() {
        super.onResume()
        startCameraSource()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when (requestCode) {
            REQUESTCODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCameraSource()
                } else {
                    activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
                    listener?.onScanFailed("Permission denied")
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

}
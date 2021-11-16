package com.barcodescanner

/**
 * Created by Venkatesh Murthy on 04/02/19.
 */

interface ScanResultListener {
    fun onScanSuccess(scanResult: ScanResult)
    fun onScanFailed(reason: String)
}
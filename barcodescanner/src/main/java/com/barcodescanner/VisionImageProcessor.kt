package com.barcodescanner

import android.graphics.Bitmap
import com.google.firebase.ml.common.FirebaseMLException
import java.nio.ByteBuffer

/**
 * Created by Venkatesh Murthy on 04/02/19.
 */

/** An inferface to process the images with different ML Kit detectors and custom image models.  */
interface VisionImageProcessor {

    /** Processes the images with the underlying machine learning models.  */
    @Throws(FirebaseMLException::class)
    fun process(data: ByteBuffer, frameMetadata: FrameMetadata, scanResultListener: ScanResultListener)

    /** Processes the bitmap images.  */
    fun process(bitmap: Bitmap, scanResultListener: ScanResultListener)

    /** Stops the underlying machine learning model and release resources.  */
    fun stop()
}
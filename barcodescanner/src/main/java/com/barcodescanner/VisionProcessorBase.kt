package com.barcodescanner

import android.graphics.Bitmap
import androidx.annotation.GuardedBy
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import java.nio.ByteBuffer

/**
 * Created by Venkatesh Murthy on 04/02/19.
 */


/**
 * Abstract base class for ML Kit frame processors. Subclasses need to implement [ ][.onSuccess] to define what they want to with
 * the detection results and [.detectInImage] to specify the detector
 * object.
 *
 * @param <T> The type of the detected feature.
</T> */
abstract class VisionProcessorBase<T> : VisionImageProcessor {

    // To keep the latest images and its metadata.
    @GuardedBy("this")
    private var latestImage: ByteBuffer? = null

    @GuardedBy("this")
    private var latestImageMetaData: FrameMetadata? = null

    // To keep the images and metadata in process.
    @GuardedBy("this")
    private var processingImage: ByteBuffer? = null

    @GuardedBy("this")
    private var processingMetaData: FrameMetadata? = null

    @Synchronized
    override fun process(
        data: ByteBuffer, frameMetadata: FrameMetadata, scanResultListener: ScanResultListener
    ) {
        latestImage = data
        latestImageMetaData = frameMetadata
        if (processingImage == null && processingMetaData == null) {
            processLatestImage(scanResultListener)
        }
    }

    // Bitmap version
    override fun process(bitmap: Bitmap, scanResultListener: ScanResultListener) {
        detectInVisionImage(
            null, FirebaseVisionImage.fromBitmap(bitmap), null,
            scanResultListener
        )/* bitmap */
    }

    @Synchronized
    private fun processLatestImage(scanResultListener: ScanResultListener) {
        processingImage = latestImage
        processingMetaData = latestImageMetaData
        latestImage = null
        latestImageMetaData = null
        if (processingImage != null && processingMetaData != null) {
            processImage(processingImage!!, processingMetaData!!, scanResultListener)
        }
    }

    private fun processImage(
        data: ByteBuffer, frameMetadata: FrameMetadata,
        scanResultListener: ScanResultListener
    ) {
        val metadata = FirebaseVisionImageMetadata.Builder()
            .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
            .setWidth(frameMetadata.width)
            .setHeight(frameMetadata.height)
            .setRotation(frameMetadata.rotation)
            .build()

        val bitmap = BitmapUtils.getBitmap(data, frameMetadata)
        detectInVisionImage(
            bitmap, FirebaseVisionImage.fromByteBuffer(data, metadata), frameMetadata,
            scanResultListener
        )
    }

    private fun detectInVisionImage(
        originalCameraImage: Bitmap?,
        image: FirebaseVisionImage,
        metadata: FrameMetadata?,
        scanResultListener: ScanResultListener
    ) {
        detectInImage(image)
            .addOnSuccessListener(
                object : OnSuccessListener<T> {
                    override fun onSuccess(results: T) {
                        this@VisionProcessorBase.onSuccess(
                            originalCameraImage, results,
                            metadata!!,
                            scanResultListener
                        )
                        processLatestImage(scanResultListener)
                    }
                })
            .addOnFailureListener(
                object : OnFailureListener {
                    override fun onFailure(e: Exception) {
                        this@VisionProcessorBase.onFailure(e, scanResultListener)
                    }
                })
    }

    override fun stop() {}

    protected abstract fun detectInImage(image: FirebaseVisionImage): Task<T>

    /**
     * Callback that executes with a successful detection result.
     *
     * @param originalCameraImage hold the original image from camera, used to draw the background
     * image.
     */
    protected abstract fun onSuccess(
        originalCameraImage: Bitmap?,
        results: T,
        frameMetadata: FrameMetadata,
        scanResultListener: ScanResultListener
    )

    protected abstract fun onFailure(e: Exception, scanResultListener: ScanResultListener)
}

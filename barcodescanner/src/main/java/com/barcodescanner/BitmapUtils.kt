package com.barcodescanner

import android.graphics.Bitmap
import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import android.graphics.BitmapFactory
import android.graphics.Matrix

import android.hardware.Camera
import android.util.Log
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

/**
 * Created by Venkatesh Murthy on 04/02/19.
 */

/** Utils functions for bitmap conversions.  */
const val ZERO_VALUE = 0
const val EIGHTY_VALUE = 80
const val NINTY_VALUE = 90
const val ONE_EIGHTY_VALUE = 180
const val TWO_SEVENTY_VALUE = 270

object BitmapUtils {

    val X_AXIS_VALUE = -1.0f
    val Y_AXIS_VALUE = 1.0f

    // Convert NV21 format byte buffer to bitmap.
    fun getBitmap(data: ByteBuffer, metadata: FrameMetadata): Bitmap? {
        data.rewind()
        val imageInBuffer = ByteArray(data.limit())
        data.get(imageInBuffer, ZERO_VALUE, imageInBuffer.size)
        try {
            val image = YuvImage(
                imageInBuffer, ImageFormat.NV21, metadata.width, metadata.height, null
            )
            if (image != null) {
                val stream = ByteArrayOutputStream()
                image.compressToJpeg(
                    Rect(
                        ZERO_VALUE, ZERO_VALUE,
                        metadata.width,
                        metadata.height
                    ), EIGHTY_VALUE, stream
                )

                val bmp = BitmapFactory.decodeByteArray(stream.toByteArray(), ZERO_VALUE, stream.size())

                stream.close()
                return rotateBitmap(bmp, metadata.rotation, metadata.cameraFacing)
            }
        } catch (e: Exception) {
            Log.e("VisionProcessorBase", "Error: " + e.message)
        }

        return null
    }

    // Rotates a bitmap if it is converted from a bytebuffer.
    private fun rotateBitmap(bitmap: Bitmap, rotation: Int, facing: Int): Bitmap {
        val matrix = Matrix()
        var rotationDegree = ZERO_VALUE
        when (rotation) {
            FirebaseVisionImageMetadata.ROTATION_90 -> rotationDegree = NINTY_VALUE
            FirebaseVisionImageMetadata.ROTATION_180 -> rotationDegree = ONE_EIGHTY_VALUE
            FirebaseVisionImageMetadata.ROTATION_270 -> rotationDegree = TWO_SEVENTY_VALUE
            else -> {
            }
        }

        // Rotate the image back to straight.}
        matrix.postRotate(rotationDegree.toFloat())
        if (facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
            return Bitmap.createBitmap(
                bitmap,
                ZERO_VALUE,
                ZERO_VALUE,
                bitmap.width,
                bitmap.height,
                matrix,
                true
            )
        } else {
            // Mirror the image along X axis for front-facing camera image.
            matrix.postScale(X_AXIS_VALUE, Y_AXIS_VALUE)
            return Bitmap.createBitmap(
                bitmap,
                ZERO_VALUE, ZERO_VALUE,
                bitmap.width,
                bitmap.height,
                matrix,
                true
            )
        }
    }
}
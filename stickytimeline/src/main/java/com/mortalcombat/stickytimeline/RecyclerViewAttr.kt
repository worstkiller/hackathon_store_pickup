package com.mortalcombat.stickytimeline

import android.graphics.drawable.Drawable

data class RecyclerViewAttr(
    val sectionBackgroundColor: Int,
    val sectionTitleTextColor: Int,
    val sectionSubTitleTextColor: Int,
    val sectionLineColor: Int,
    val sectionCircleColor: Int,
    val sectionStrokeColor: Int,
    val sectionTitleTextSize: Float,
    val sectionSubTitleTextSize: Float,
    val sectionLineWidth: Float,
    val isSticky: Boolean,
    val customDotDrawable: Drawable?,
    val mode: Int
)

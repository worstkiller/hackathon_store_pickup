package com.mortalcombat.stickytimeline

import android.graphics.drawable.Drawable

data class SectionInfo(
    val title: String,
    val subTitle: String? = null,
    val dotDrawable: Drawable? = null
)

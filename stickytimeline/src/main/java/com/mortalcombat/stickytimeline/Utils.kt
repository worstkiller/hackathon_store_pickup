package com.mortalcombat.stickytimeline

import android.content.Context

object Utils {
    fun Int.dpToValue(context: Context): Float = this * context.resources.displayMetrics.density
}
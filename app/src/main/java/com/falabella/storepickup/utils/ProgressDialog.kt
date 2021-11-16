package com.falabella.storepickup.utils

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.falabella.storepickup.databinding.DialogProgressBinding
import com.falabella.storepickup.utils.UiUtils.doToPx

/**
 * view class to show the progress dialog
 */
class ProgressDialog(context: Context) : AlertDialog(context) {

    lateinit var binding: DialogProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogProgressBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        setCancelable(false)
        window?.setLayout(110.doToPx(), 110.doToPx())
    }

    companion object {

        fun getInstance(context: Context?): ProgressDialog? {
            return context?.let { ProgressDialog(it) }
        }

    }

}
package com.falabella.storepickup.barcode

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.falabella.storepickup.databinding.LayoutAlertDialogBinding
import com.falabella.storepickup.home.HomeActivity

class ScanAlertDialog : DialogFragment() {

    lateinit var binding: LayoutAlertDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val isDeliveryComplete = requireArguments().getBoolean(DELIVERY_STATUS, false)
        binding = LayoutAlertDialogBinding.inflate(LayoutInflater.from(context))
        binding.tvDescription.text = if(isDeliveryComplete) "¡Entrega completa!" else "Ups! Error en la entrega"
        binding.animationViewSuccess.visibility = if(isDeliveryComplete) View.VISIBLE else View.GONE
        binding.animationViewFailure.visibility = if(isDeliveryComplete) View.GONE else View.VISIBLE
        isCancelable = true
        binding.btnGoToHome.setOnClickListener {
            val intent = Intent(activity, HomeActivity::class.java)
            activity?.startActivity(intent)
        }
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    companion object {
        private const val DELIVERY_STATUS = "deliveryStatus"
        fun newInstance(isDeliveryComplete: Boolean): ScanAlertDialog {
            val frag = ScanAlertDialog()
            val args = Bundle()
            args.putBoolean(DELIVERY_STATUS, isDeliveryComplete)
            frag.setArguments(args)
            return frag
        }
    }
}
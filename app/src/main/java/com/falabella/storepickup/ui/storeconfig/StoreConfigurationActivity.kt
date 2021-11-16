package com.falabella.storepickup.ui.storeconfig

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.falabella.storepickup.databinding.ActivityStoreConfigurationBinding
import java.text.DateFormat
import java.util.*

/**
 * to show the store configuration ui
 */
class StoreConfigurationActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    lateinit var binding: ActivityStoreConfigurationBinding
    lateinit var viewModel: StoreConfigurationViewModel
    lateinit var datePickerDialog: DatePickerDialog

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreConfigurationBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        viewModel = defaultViewModelProviderFactory.create(StoreConfigurationViewModel::class.java)
        datePickerDialog = DatePickerDialog(this).also {
            it.setOnDateSetListener(this@StoreConfigurationActivity)
        }
        setUpStoreConfig()
    }

    private fun setUpStoreConfig() {
        viewModel.getDummyData().apply {
            viewModel.selectedStore
            binding.tvStoreName.text = storeName
            binding.tvNoOfCustomerCount.text = customersByDefault.toString()
            binding.tvDateSelected.text = DateFormat.getDateInstance().format(Date(date))
        }

        with(binding) {

            ivAddButton.setOnClickListener {
                val count = "${(tvNoOfCustomerCount.text.toString().toIntOrNull() ?: 0).inc()}"
                tvNoOfCustomerCount.text = count
            }

            ivRemoveButton.setOnClickListener {
                val count = tvNoOfCustomerCount.text.toString().toIntOrNull() ?: 0
                if (count > 0) {
                    tvNoOfCustomerCount.text =
                        "${count.dec()}"
                }
            }

            tvDateSelected.setOnClickListener {
                datePickerDialog.show()
            }

            rangeSelector.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    tvRangeSelected.text = "${p1.times(30)}: min"
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}

                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.DAY_OF_MONTH, day)
        binding.tvDateSelected.text = DateFormat.getDateInstance().format(Date(cal.time.time))
    }

}
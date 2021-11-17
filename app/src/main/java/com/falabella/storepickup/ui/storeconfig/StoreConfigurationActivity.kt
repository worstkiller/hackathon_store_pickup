package com.falabella.storepickup.ui.storeconfig

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.falabella.storepickup.R
import com.falabella.storepickup.databinding.ActivityStoreConfigurationBinding
import com.falabella.storepickup.model.StoreConfigurationModel
import com.falabella.storepickup.model.StoreSlots
import com.falabella.storepickup.utils.ProgressDialog
import com.falabella.storepickup.utils.UiUtils.isVisible
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * to show the store configuration ui
 */
class StoreConfigurationActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    lateinit var binding: ActivityStoreConfigurationBinding
    lateinit var viewModel: StoreConfigurationViewModel
    lateinit var datePickerDialog: DatePickerDialog
    lateinit var adaper: StoreAdapter
    lateinit var progress: ProgressDialog

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
        adaper = StoreAdapter()
        progress = ProgressDialog(this)
        setListener()
        observeData()
    }

    private fun observeData() {
        viewModel.selectedStoreConfig.observe(this) {
            progress.hide()
            if (it != null) {
                setUpStoreConfig(it)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        progress.show()
        viewModel.getAllStores()
    }

    private fun setUpStoreConfig(storeConfigurationModel: StoreConfigurationModel?) {
        storeConfigurationModel?.apply {
            binding.tvStoreAddress.text = directions
            "Espacio: ${storeSlots.size} (${storeStartTime} a ${storeEndTime} hrs)".also {
                binding.tvStoreSlots.text = it
            }
            binding.tvStoreName.text = storeName
            binding.tvNoOfCustomerCount.text = customersByDefault.toString()
            binding.tvDateSelected.text =
                DateFormat.getDateInstance().format(Date().time)
            adaper.updateData(storeSlots)
            checkVisibility(storeSlots.isNotEmpty())
            viewModel.selectedStore?.date = Date().time
            viewModel.selectedStore = this
            validateButton()
        }
    }

    private fun setListener() {
        with(binding) {

            tvStoreName.setOnClickListener {
                showDialog()
            }

            clStoreHolder.setOnClickListener {
                showDialog()
            }

            rvSlots.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(this@StoreConfigurationActivity, 3)
                adapter = this@StoreConfigurationActivity.adaper
            }

            ivAddButton.setOnClickListener {
                val no = (tvNoOfCustomerCount.text.toString().toIntOrNull() ?: 0).inc()
                val count = "$no"
                tvNoOfCustomerCount.text = count
                viewModel.selectedStore?.customersByDefault = no
                validateButton()
            }

            ivRemoveButton.setOnClickListener {
                val count = tvNoOfCustomerCount.text.toString().toIntOrNull() ?: 0
                if (count > 0) {
                    tvNoOfCustomerCount.text =
                        "${count.dec()}"
                }
                viewModel.selectedStore?.customersByDefault = count
                validateButton()
            }

            tvDateSelected.setOnClickListener {
                datePickerDialog.show()
            }

            rangeSelector.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    val range = p1.times(30)
                    viewModel.selectedStore?.slotRange = range
                    tvRangeSelected.text = "$range: min"
                    inflateSlots()
                    validateButton()
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}

                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            btnUpdateStore.setOnClickListener {
                progress.show()
                viewModel.selectedStore?.let { it1 ->
                    viewModel.updateData(it1) { success ->
                        progress.hide()
                        if (success) {
                            Toast.makeText(
                                this@StoreConfigurationActivity,
                                getString(R.string.success_store_add),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@StoreConfigurationActivity,
                                getString(R.string.store_update_failure),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun validateButton() {
        binding.btnUpdateStore.isEnabled = isValid()
    }

    private fun checkVisibility(isVisible: Boolean) {
        binding.btnAddSlots.isVisible(isVisible.not())
        binding.rvSlots.isVisible(isVisible)
    }

    private fun inflateSlots() {
        val calendarStart = Calendar.getInstance().apply {
            time = Date(viewModel.selectedStore?.date ?: System.currentTimeMillis())
            set(Calendar.HOUR_OF_DAY, viewModel.selectedStore?.storeStartTime ?: 0)
            time
        }
        val calendarEnd = Calendar.getInstance().apply {
            time = Date(viewModel.selectedStore?.date ?: System.currentTimeMillis())
            set(Calendar.HOUR_OF_DAY, viewModel.selectedStore?.storeEndTime ?: 0)
            time
        }

        val minsDiff = calendarEnd.time.time - calendarStart.time.time
        val timeInMinustsRemaing =
            TimeUnit.MILLISECONDS.toMinutes(minsDiff) / (viewModel.selectedStore?.slotRange ?: 30)
        val range = arrayListOf<StoreSlots>()
        repeat(timeInMinustsRemaing.toInt()) {
            val cal = Calendar.getInstance().apply {
                time = Date(calendarStart.time.time)
                add(Calendar.MINUTE, viewModel.selectedStore?.slotRange?.times(it) ?: 30)
                time
            }
            val endCal = Calendar.getInstance().apply {
                time = Date(cal.time.time)
                add(Calendar.MINUTE, viewModel.selectedStore?.slotRange?.times(it) ?: 30)
                time
            }
            val formatTime = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val isEnabled = cal.time.before(Date())
            val slotRange = StoreSlots(
                cal.time.time,
                formatTime.format(cal.time),
                "${cal.time.time}",
                "${endCal.time.time}",
                isEnabled.not(),
                viewModel.selectedStore?.customersByDefault ?: 1
            )
            range.add(slotRange)
        }
        adaper.updateData(range)
        viewModel.selectedStore?.storeSlots = range
        checkVisibility(range.isNotEmpty())
        validateButton()
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.DAY_OF_MONTH, day)
        cal.time
        binding.tvDateSelected.text = DateFormat.getDateInstance().format(Date(cal.time.time))
        viewModel.selectedStore?.date = cal.time.time
        validateButton()
    }

    private fun isValid(): Boolean {
        return !(viewModel.selectedStore == null
                ||
                viewModel.selectedStore?.slotRange == 0
                ||
                viewModel.selectedStore?.date == 0L
                ||
                viewModel.selectedStore?.customersByDefault == 0
                ||
                viewModel.selectedStore?.storeSlots?.isEmpty() ?: false)
    }

    private fun showDialog() {
        // setup the alert builder
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.selecet_store))
        val stores = viewModel.listOfStores.value?.map { it.storeName }?.toTypedArray()
        builder.setItems(stores) { dialog, which ->
            viewModel.updateSelectedStore(which)
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

}
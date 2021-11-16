package com.falabella.storepickup.ui.storeconfig

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.falabella.storepickup.R
import com.falabella.storepickup.databinding.ItemSlotSelectionBinding
import com.falabella.storepickup.model.StoreSlots

class StoreAdapter : RecyclerView.Adapter<StoreAdapter.ViewHolder>() {

    val dataList = arrayListOf<StoreSlots>()

    class ViewHolder(val view: ItemSlotSelectionBinding) : RecyclerView.ViewHolder(view.root) {

        companion object {
            fun getInstance(viewGroup: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(viewGroup.context)
                val binding = ItemSlotSelectionBinding.inflate(inflater, viewGroup, false)
                return ViewHolder(binding)
            }
        }

        fun bind(data: StoreSlots) {
            val back = if (data.enabled) R.drawable.round_box else R.drawable.round_box_filled
            view.tvSlotRange.text = data.range
            view.root.setBackgroundResource(back)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.getInstance(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(slots: List<StoreSlots>) {
        dataList.clear()
        dataList.addAll(slots)
        notifyDataSetChanged()
    }
}
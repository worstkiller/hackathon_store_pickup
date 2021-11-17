package com.falabella.storepickup.orderlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.falabella.storepickup.R
import com.falabella.storepickup.model.StoreAppointmentModel
import java.util.Locale

class OrderListAdapter(
    private val layoutInflater: LayoutInflater,
    private val list: List<StoreAppointmentModel>,
    private val clickListener: ItemClickListener? = null,
    @param:LayoutRes private val rowLayout: Int,
) : RecyclerView.Adapter<OrderListAdapter.ViewHolder>(), Filterable {

    private var orderFilterList = mutableListOf<StoreAppointmentModel>()
    init {
        orderFilterList.addAll(list)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater.inflate(
            rowLayout,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val storeAppointmentModel = list[position]
        holder.orderIdTv.text = storeAppointmentModel.orderNo
        holder.productsSizeTv.text = storeAppointmentModel.startTime.toString()
        holder.customerNameTv.text = storeAppointmentModel.appointmentId
        holder.timeTv.text = storeAppointmentModel.startTimeSlot
        holder.itemView.setOnClickListener {
            clickListener?.onOrderClicked(storeAppointmentModel)
        }
    }

    override fun getItemCount(): Int = orderFilterList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                orderFilterList.clear()
                if (charSearch.isEmpty()) {
                    orderFilterList.addAll(list)
                } else {
                    orderFilterList.addAll(
                        list.filter {
                            it.orderNo?.lowercase(Locale.ROOT)
                                ?.contains(charSearch.lowercase(Locale.ROOT)) == true
                        }
                    )
                }
                return FilterResults().apply { values = orderFilterList }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                orderFilterList = results?.values as ArrayList<StoreAppointmentModel>
                notifyDataSetChanged()
            }

        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val orderIdTv: TextView = view.findViewById<View>(R.id.orderIdTv) as TextView
        val productsSizeTv: TextView = view.findViewById<View>(R.id.productsSizeTv) as TextView
        val customerNameTv: TextView = view.findViewById<View>(R.id.customerNameTv) as TextView
        val timeTv: TextView = view.findViewById<View>(R.id.timeTv) as TextView
    }

    interface ItemClickListener {
        fun onOrderClicked(storeAppointmentModel: StoreAppointmentModel)
    }
}

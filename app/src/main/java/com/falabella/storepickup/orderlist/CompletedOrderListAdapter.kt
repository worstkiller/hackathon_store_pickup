package com.falabella.storepickup.orderlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.falabella.storepickup.R
import com.falabella.storepickup.model.StoreAppointmentModel
import com.falabella.storepickup.orderlist.UpcomingOrderListAdapter.ItemClickListener

class CompletedOrderListAdapter(
    private val layoutInflater: LayoutInflater,
    private val list: List<StoreAppointmentModel>,
    private val clickListener: ItemClickListener? = null,
    @param:LayoutRes private val rowLayout: Int,
) : RecyclerView.Adapter<CompletedOrderListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater.inflate(
            rowLayout,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val storeAppointmentModel = list[position]
        val orderId = "#$position$position$position$position"
        holder.orderIdTv.text = orderId
        holder.productsSizeTv.text = (storeAppointmentModel.products?.size ?: 1).toString()
        holder.customerNameTv.text = storeAppointmentModel.customerName
        holder.itemView.setOnClickListener {
            clickListener?.onOrderClicked(orderId, storeAppointmentModel)
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val orderIdTv: TextView = view.findViewById<View>(R.id.orderIdTv) as TextView
        val productsSizeTv: TextView = view.findViewById<View>(R.id.productsSizeTv) as TextView
        val customerNameTv: TextView = view.findViewById<View>(R.id.customerNameTv) as TextView
    }
}

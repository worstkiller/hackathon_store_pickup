package com.falabella.storepickup.utils

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.falabella.storepickup.R
import com.falabella.storepickup.model.StoreAppointmentModel
import com.mortalcombat.stickytimeline.SectionInfo
import com.mortalcombat.stickytimeline.VerticalSectionItemDecoration

object UiUtils {

    //Get SectionCallback method
    fun getSectionCallback(orderList: List<StoreAppointmentModel>, context: Context):
        VerticalSectionItemDecoration
    .SectionCallback {
        return object : VerticalSectionItemDecoration.SectionCallback {
            //In your data, implement a method to determine if this is a section.
            override fun isSection(position: Int): Boolean =
                orderList[position].startTime != orderList[position - 1].startTime

            //Implement a method that returns a SectionHeader.
            override fun getSectionHeader(position: Int): SectionInfo {
                val storeAppointmentModel = orderList[position]
                val dot: Int = when (storeAppointmentModel.appointmentId) {
                    "FIN.K.L" -> {
                        R.drawable.ic_finkl
                    }
                    "Girls' Generation" -> {
                        R.drawable.ic_girlsgeneration
                    }
                    "Buzz" -> {
                        R.drawable.ic_buzz
                    }
                    "Wanna One" -> {
                        R.drawable.ic_wannaone
                    }
                    else -> R.drawable.ic_solo
                }
                return SectionInfo(
                    title = storeAppointmentModel.startTime.orEmpty(),
                    subTitle = "${orderList.count { storeAppointmentModel.startTime == it.startTime}} Products",
                    dotDrawable = AppCompatResources.getDrawable(context, dot)
                )
            }
        }
    }
}
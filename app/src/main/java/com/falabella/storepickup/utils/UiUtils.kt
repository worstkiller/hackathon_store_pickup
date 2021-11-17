package com.falabella.storepickup.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.NotificationCompat
import com.falabella.storepickup.R
import com.falabella.storepickup.model.StoreAppointmentModel
import com.mortalcombat.stickytimeline.SectionInfo
import com.mortalcombat.stickytimeline.VerticalSectionItemDecoration
import java.text.SimpleDateFormat
import java.util.*

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
                    title = storeAppointmentModel.startDate.orEmpty(),
                    subTitle = "${orderList.count { storeAppointmentModel.startDate == it.startDate }} " + context.getString(
                        R.string.appointments
                    ),
                    dotDrawable = AppCompatResources.getDrawable(context, dot)
                )
            }
        }
    }

    fun View.isVisible(visible: Boolean) {
        visibility = if (visible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun Int.doToPx(): Int {
        return this * Resources.getSystem().displayMetrics.density.toInt()
    }

    /**
     * Return date in specified format.
     * @param milliSeconds Date in milliseconds
     * @param dateFormat Date format
     * @return String representing date in specified format
     */
    fun getSimpleDateTimeFormat(
        milliSeconds: Long,
        dateFormat: String? = OrderConstants.SIMPLE_DATE_FORMAT
    ): String? {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }

    fun showNotifications(title: String, body: String, context: Context) {
        val builder = NotificationCompat.Builder(context, "inStorePickup")
            .setSmallIcon(R.drawable.store)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "inStorePickup"
            val descriptionText = "inStorePickup"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("inStorePickup", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            notificationManager.notify("", 101, builder.build())
        }

    }
}
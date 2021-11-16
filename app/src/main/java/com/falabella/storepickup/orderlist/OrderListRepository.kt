package com.falabella.storepickup.orderlist

import com.falabella.storepickup.model.StoreAppointmentModel

class OrderListRepository {

    private val mockOrderList: List<StoreAppointmentModel> =
        listOf(
            StoreAppointmentModel(
                appointmentId = "Solo",
                startTime = 1637082468989,
                customerName = "Lim Chang Jung",
                storeId = "001",
                endTime = 0,
                orderNo = "000001",
                customerId = "1",
                range = "",
                orderPrice = "200$",
                slotId = "1234",
                documentNo = "1doc",
                emptyList(),
                isCompleted = false
            ),
            StoreAppointmentModel(
                appointmentId = "FIN.K.L",
                startTime = 1637082460000,
                customerName = "Lee Jin",
                storeId = "002",
                endTime = 0,
                orderNo = "000001",
                customerId = "1",
                range = "",
                orderPrice = "200$",
                slotId = "1235",
                documentNo = "1doc",
                emptyList(),
                isCompleted = true
            ),
            StoreAppointmentModel(
                appointmentId = "FIN.K.L",
                startTime = 2637082368989,
                customerName = "Sung Yu Ri",
                storeId = "002",
                endTime = 0,
                orderNo = "000001",
                customerId = "1",
                range = "",
                orderPrice = "200$",
                slotId = "1234",
                documentNo = "1doc",
                emptyList(),
                isCompleted = true
            ),
            StoreAppointmentModel(
                appointmentId = "FIN.K.L",
                startTime = 0,
                customerName = "Oak Joo Hyun",
                storeId = "003",
                endTime = 0,
                orderNo = "000001",
                customerId = "1",
                range = "",
                orderPrice = "200$",
                slotId = "1234",
                documentNo = "1doc",
                emptyList(),
                isCompleted = true
            ),
            StoreAppointmentModel(
                appointmentId = "FIN.K.L",
                startTime = 0,
                customerName = "Lee Hyo Ri",
                storeId = "001",
                endTime = 0,
                orderNo = "000001",
                customerId = "1",
                range = "",
                orderPrice = "100$",
                slotId = "1234",
                documentNo = "5doc",
                emptyList(),
                isCompleted = true
            ),
            StoreAppointmentModel(
                appointmentId = "Solo",
                startTime = 0,
                isCompleted = true,
                customerName = "Kim Bumsoo"
            ),
            StoreAppointmentModel(
                appointmentId = "Solo",
                startTime = 0,
                isCompleted = true,
                customerName = "Park Hyo Shin"
            ),
            StoreAppointmentModel(
                appointmentId = "Solo",
                startTime = 0,
                isCompleted = true,
                customerName = "Lee Soo Young"
            ),
            StoreAppointmentModel(
                appointmentId = "Solo",
                startTime = 0,
                isCompleted = true,
                customerName = "Sung Si Kyung"
            ),
            StoreAppointmentModel(
                appointmentId = "Buzz",
                startTime = 0,
                isCompleted = true,
                customerName = "Kim Yeah"
            ),
            StoreAppointmentModel(
                appointmentId = "Buzz",
                startTime = 0,
                isCompleted = true,
                customerName = "Yun Woo Hyun"
            ),
            StoreAppointmentModel(
                appointmentId = "Buzz",
                startTime = 0,
                isCompleted = true,
                customerName = "Sin Jun Ki"
            ),
            StoreAppointmentModel(
                appointmentId = "Buzz",
                startTime = 0,
                isCompleted = true,
                customerName = "Min Kyung Hoon"
            ),
            StoreAppointmentModel(
                appointmentId = "Solo",
                startTime = 1637042468983,
                customerName = "Yunha"
            ),
            StoreAppointmentModel(
                appointmentId = "Girls' Generation",
                startTime = 1637082468920,
                isCompleted = true,
                customerName = "TaeYeon"
            ),
            StoreAppointmentModel(
                appointmentId = "Girls' Generation",
                startTime = 1637042468983,
                customerName = "Sunny"
            ),
            StoreAppointmentModel(
                "Girls' Generation",
                1637282468989,
                "Tiffany"
            ),
            StoreAppointmentModel(
                "Girls' Generation",
                0,
                "HyoYeon"
            ),
            StoreAppointmentModel(
                "Girls' Generation",
                0,
                "YuRi"
            ),
            StoreAppointmentModel(
                "Girls' Generation",
                0,
                "SooYoung"
            ),
            StoreAppointmentModel(
                "Girls' Generation",
                0,
                "YoonA"
            ),
            StoreAppointmentModel(
                "Girls' Generation",
                0,
                "SeoHyun"
            ),
            StoreAppointmentModel(
                "Wanna One",
                0,
                "Kang Daniel"
            ),
            StoreAppointmentModel(
                "Wanna One",
                0,
                "Lai Kuan Lin"
            ),
            StoreAppointmentModel(
                "Wanna One",
                0,
                "Ong Seong Wu"
            ),
            StoreAppointmentModel(
                "Wanna One",
                0,
                "Ha Sung Woon"
            ),
            StoreAppointmentModel(
                "Wanna One",
                0,
                "Yoon Ji Sung"
            ),
            StoreAppointmentModel(
                "Wanna One",
                0,
                "Park Woo Jin"
            ),
            StoreAppointmentModel(
                "Wanna One",
                0,
                "Lee Dae Hwi"
            )
        )

    fun getUpcomingList() = with(mockOrderList) {
        forEach { storeAppointmentModel ->  storeAppointmentModel.updateTimeValues() }
        sortedBy { it.startTime }
        filter { it.isCompleted.not() }
    }

    fun getCompletedList() = with(mockOrderList) {
        forEach { storeAppointmentModel ->  storeAppointmentModel.updateTimeValues() }
        sortedByDescending { it.startTime }
        filter {  it.isCompleted }
    }
}

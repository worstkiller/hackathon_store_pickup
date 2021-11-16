package com.falabella.storepickup.orderlist

import com.falabella.storepickup.model.StoreAppointmentModel

class OrderListRepository {

    //solo
    private val mockOrderList: List<StoreAppointmentModel> =
        listOf(
            StoreAppointmentModel(
                appointmentId = "Solo",
                startTime = "1995.04",
                customerName = "Lim Chang Jung",
                storeId = "001",
                endTime = "1995.06",
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
                startTime = "1998.05",
                customerName = "Lee Jin",
                storeId = "002",
                endTime = "1998.06",
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
                startTime = "1998.05",
                customerName = "Sung Yu Ri",
                storeId = "002",
                endTime = "1995.06",
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
                startTime = "1996.06",
                customerName = "Oak Joo Hyun",
                storeId = "003",
                endTime = "1995.06",
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
                startTime = "1995.05",
                customerName = "Lee Hyo Ri",
                storeId = "001",
                endTime = "1995.06",
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
                startTime = "1999.04",
                customerName = "Kim Bumsoo"
            ),
            StoreAppointmentModel(
                appointmentId = "Solo",
                startTime = "1999.11",
                customerName = "Park Hyo Shin"
            ),
            StoreAppointmentModel(
                appointmentId = "Solo",
                startTime = "1999.11",
                customerName = "Lee Soo Young"
            ),
            StoreAppointmentModel(
                appointmentId = "Solo",
                startTime = "2000.11",
                customerName = "Sung Si Kyung"
            ),
            StoreAppointmentModel(
                appointmentId = "Buzz",
                startTime = "2003.10",
                customerName = "Kim Yeah"
            ),
            StoreAppointmentModel(
                appointmentId = "Buzz",
                startTime = "2003.10",
                customerName = "Yun Woo Hyun"
            ),
            StoreAppointmentModel(
                "Buzz",
                "2003.10",
                "Sin Jun Ki"
            ),
            StoreAppointmentModel(
                "Buzz",
                "2003.10",
                "Min Kyung Hoon"
            ),
            StoreAppointmentModel(
                "Solo",
                "2006.06",
                "Yunha"
            ),
            StoreAppointmentModel(
                "Girls' Generation",
                "2007.08",
                "TaeYeon"
            ),
            StoreAppointmentModel(
                "Girls' Generation",
                "2007.08",
                "Sunny"
            ),
            StoreAppointmentModel(
                "Girls' Generation",
                "2007.08",
                "Tiffany"
            ),
            StoreAppointmentModel(
                "Girls' Generation",
                "2007.08",
                "HyoYeon"
            ),
            StoreAppointmentModel(
                "Girls' Generation",
                "2007.08",
                "YuRi"
            ),
            StoreAppointmentModel(
                "Girls' Generation",
                "2007.08",
                "SooYoung"
            ),
            StoreAppointmentModel(
                "Girls' Generation",
                "2007.08",
                "YoonA"
            ),
            StoreAppointmentModel(
                "Girls' Generation",
                "2007.08",
                "SeoHyun"
            ),
            StoreAppointmentModel(
                "Wanna One",
                "2017.08",
                "Kang Daniel"
            ),
            StoreAppointmentModel(
                "Wanna One",
                "2017.08",
                "Lai Kuan Lin"
            ),
            StoreAppointmentModel(
                "Wanna One",
                "2017.08",
                "Ong Seong Wu"
            ),
            StoreAppointmentModel(
                "Wanna One",
                "2017.08",
                "Ha Sung Woon"
            ),
            StoreAppointmentModel(
                "Wanna One",
                "2017.08",
                "Yoon Ji Sung"
            ),
            StoreAppointmentModel(
                "Wanna One",
                "2017.08",
                "Park Woo Jin"
            ),
            StoreAppointmentModel(
                "Wanna One",
                "2017.08",
                "Lee Dae Hwi"
            ),
            StoreAppointmentModel(
                "Wanna One",
                "2017.08",
                "Kim Jae Hwan"
            ),
            StoreAppointmentModel(
                "Wanna One",
                "2017.08",
                "Bae Jin Young"
            ),
            StoreAppointmentModel(
                "Wanna One",
                "2017.08",
                "Hwang Min Hyun"
            ),
            StoreAppointmentModel(
                "Wanna One",
                "2017.08",
                "Park Ji Hoon"
            ),
            StoreAppointmentModel(
                "Solo",
                "2017.11",
                "Woo Won Jae"
            ),
            StoreAppointmentModel(
                "Solo",
                "2017.11",
                "Me Won Jae"
            )
        )

    fun getUpcomingList() = mockOrderList.filter { it.isCompleted.not() }

    fun getCompletedList() = mockOrderList.filter { it.isCompleted }
}

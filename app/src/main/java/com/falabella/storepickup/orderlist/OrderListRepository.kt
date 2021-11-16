package com.falabella.storepickup.orderlist

import com.falabella.storepickup.model.StoreAppointmentModel

class OrderListRepository {

    //solo
    private val mockOrderList: List<StoreAppointmentModel> =
        listOf(
            StoreAppointmentModel(
                "Solo",
                "1995.04",
                "Lim Chang Jung"
            ),
            StoreAppointmentModel(
                "FIN.K.L",
                "1998.05",
                "Lee Jin"
            ),
            StoreAppointmentModel(
                "FIN.K.L",
                "1998.05",
                "Sung Yu Ri"
            ),
            StoreAppointmentModel(
                "FIN.K.L",
                "1998.05",
                "Oak Joo Hyun"
            ),
            StoreAppointmentModel(
                "FIN.K.L",
                "1998.05",
                "Lee Hyo Ri"
            ),
            StoreAppointmentModel(
                "Solo",
                "1999.04",
                "Kim Bumsoo"
            ),
            StoreAppointmentModel(
                "Solo",
                "1999.11",
                "Park Hyo Shin"
            ),
            StoreAppointmentModel(
                "Solo",
                "1999.11",
                "Lee Soo Young"
            ),
            StoreAppointmentModel(
                "Solo",
                "2000.11",
                "Sung Si Kyung"
            ),
            StoreAppointmentModel(
                "Buzz",
                "2003.10",
                "Kim Yeah"
            ),
            StoreAppointmentModel(
                "Buzz",
                "2003.10",
                "Yun Woo Hyun"
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

    fun getUpcomingList() = mockOrderList.subList(0, 17)

    fun getCompletedList() = mockOrderList.subList(18, mockOrderList.size)
}

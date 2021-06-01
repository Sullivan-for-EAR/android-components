package com.sullivan.common.ui_common.navigator

import android.app.Activity

interface LoginNavigator {
    fun openLogin(activity: Activity)
}

interface ReservationNavigator {
    fun openReservationHome(activity: Activity)
    fun openRealTimeReservationHome(activity: Activity)
}
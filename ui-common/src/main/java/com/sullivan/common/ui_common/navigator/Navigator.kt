package com.sullivan.common.ui_common.navigator

import android.content.Context

interface LoginNavigator {
    fun openLogin(context: Context)
}

interface ReservationNavigator {
    fun openReservationHome(context: Context)
    fun openRealTimeReservationHome(context: Context)
}
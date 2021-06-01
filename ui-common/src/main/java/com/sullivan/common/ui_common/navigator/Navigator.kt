package com.sullivan.common.ui_common.navigator

import android.app.Activity
import android.content.Context

interface LoginNavigator {
    fun openLogin(activity: Activity)
}

interface ReservationNavigator {
    fun openReservationHome(activity: Activity)
}
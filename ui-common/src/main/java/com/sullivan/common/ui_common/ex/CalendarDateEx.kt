package com.sullivan.common.ui_common.ex

import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun Calendar.year() = get(Calendar.YEAR)
fun Calendar.month() = get(Calendar.MONTH)
fun Calendar.day() = get(Calendar.DAY_OF_MONTH)

fun Calendar.getCurrentDayOfName(): String {
    val date = this.time
    val simpleDateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
    return simpleDateFormat.format(date)
}

fun String.convertDate(): String {
    val format = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    try {
        calendar.time = format.parse(this)!!

    } catch (e: ParseException) {
        Timber.e(e)
    }
    return "${calendar.get(Calendar.MONTH) + 1}월 ${calendar.get(Calendar.DAY_OF_MONTH)}일 ${
        calendar.getCurrentDayOfName()
    }"
}

fun String.getTimeInfo(): String {

    val hour = this.substring(0, 2).toInt()
    val minute = this.substring(2).toInt()

    if (hour <= 12) {
        return if (hour <= 9) {
            if (minute <= 9) {
                "오전 0$hour:0$minute"
            } else {
                "오전 0$hour:$minute"
            }
        } else {
            if (minute <= 9) {
                "오전 $hour:0$minute"
            } else {
                "오전 $hour:$minute"
            }
        }
    } else {
        return if (hour <= 9) {
            if (minute <= 9) {
                "오후 0${hour - 12}:0$minute"
            } else {
                "오후 0${hour - 12}:$minute"
            }
        } else {
            if (minute <= 9) {
                "오후 ${hour - 12}:0$minute"
            } else {
                "오후 ${hour - 12}:$minute"
            }
        }
    }
}
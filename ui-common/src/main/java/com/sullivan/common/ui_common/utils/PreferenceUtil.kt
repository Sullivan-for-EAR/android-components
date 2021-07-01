package com.sullivan.common.ui_common.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.GsonBuilder
import javax.inject.Inject

object PreferenceUtil {
    fun getPref(context: Context) =
        context.getSharedPreferences(PreferenceUtil.javaClass.name, Context.MODE_PRIVATE)
}

class SharedPreferenceManager @Inject constructor(
    private val pref: SharedPreferences
) {
    fun getAccessToken() = pref.getString(TOKEN, "")

    fun setAccessToken(token: String) {
        pref.edit { putString(TOKEN, token) }
    }

    fun getUserId() = pref.getInt(USER_ID, 0)

    fun setUserId(id: Int) {

        pref.edit {
            putInt(USER_ID, id)
        }
    }

//    fun getUserPHONE() = pref.getString(USER_PHONE, "")
//
//    fun setUserPHONE(userPHONE: String) {
//
//        pref.edit {
//            putString(USER_PHONE, userPHONE)
//        }
//    }

    companion object {
        const val TOKEN = "token"
        const val USER_ID = "user_id"
    }
}

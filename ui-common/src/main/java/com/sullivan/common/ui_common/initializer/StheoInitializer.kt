package com.sullivan.common.ui_common.initializer

import android.content.Context
import androidx.startup.Initializer
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.sullivan.common.ui_common.BuildConfig
import okhttp3.OkHttpClient

class StheoInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}

package com.sullivan.common.ui_common.di

import android.content.Context
import com.sullivan.common.ui_common.utils.SharedPreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    fun providePreferenceManager(@ApplicationContext context: Context): SharedPreferenceManager =
        SharedPreferenceManager(
            context.getSharedPreferences(
                UtilsModule.javaClass.name,
                Context.MODE_PRIVATE
            )
        )
}
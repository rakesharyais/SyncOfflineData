package com.example.syncofflinedata.di

import android.content.Context
import androidx.work.WorkManager
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    fun providesWorkManager(
        @ApplicationContext context: Context,
    ):WorkManager = WorkManager.getInstance(context)
}
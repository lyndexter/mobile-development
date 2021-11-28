package com.lyndexter.androiddevelopment

import android.app.Application
import timber.log.Timber

class MobileLab : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
package com.github.amin.quotee

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class QuoteeApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
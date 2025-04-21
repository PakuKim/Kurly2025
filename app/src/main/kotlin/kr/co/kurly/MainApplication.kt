package kr.co.kurly

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
internal class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

    }
}
package com.example.moodcheck

import android.app.Application
import com.example.moodcheck.data.AppContainer
import com.example.moodcheck.data.AppDataContainer

class MoodApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}

package com.itechart.covid_tracker.app_level.notification

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.lifecycle.ViewModelProvider
import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.screens.settings.SettingsViewModel
import kotlin.concurrent.thread

class NotificationService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        registerNotificationChannel(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val thisService = this
        thread {                     //shows a notification in 5 seconds
            Thread.sleep(5000)

            val settings = Model.loadSettings()
            if (settings.notifications)
                showNotification(thisService)
        }
        return super.onStartCommand(intent, flags, startId)
    }
}
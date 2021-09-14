package com.itechart.covid_tracker.app_level.notification

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.database.settings.SettingsProvider
import javax.inject.Inject
import kotlin.concurrent.thread

class NotificationService: Service() {
    @Inject
    lateinit var settingsProvider: SettingsProvider

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        App.appComponent.inject(this)

        registerNotificationChannel(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val thisService = this
        thread {                     //shows a notification in 5 seconds
            Thread.sleep(5000)

            val settings = settingsProvider.loadSettings()
            if (settings.notifications)
                showNotification(thisService)
        }
        return super.onStartCommand(intent, flags, startId)
    }
}
package com.itechart.covid_tracker.app_level.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.itechart.covid_tracker.R

const val CHANNEL_ID = "CHANNEL_ID"

fun registerNotificationChannel(service: Service){
    val channel = NotificationChannel(CHANNEL_ID, service.getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT).apply {
        description = "Description"
    }
    // Register the channel with the system
    val notificationManager = service.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
}

fun showNotification(service: Service){
    val notification = NotificationCompat.Builder(service, CHANNEL_ID) //notification creating
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(service.getString(R.string.app_name))
            .setContentText("Don't forget to take anticovid measures")
            .build()

    val notificationManager = service.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(1, notification)
}
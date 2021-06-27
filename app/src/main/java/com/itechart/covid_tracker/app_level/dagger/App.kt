package com.itechart.covid_tracker.app_level.dagger

import android.app.Application

object App: Application() {
    val appComponent = DaggerAppComponent.builder().appModule(AppModule(applicationContext)).build()
}
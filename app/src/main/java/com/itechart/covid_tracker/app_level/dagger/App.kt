package com.itechart.covid_tracker.app_level.dagger

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(applicationContext)).build()
    }

    companion object{
        lateinit var appComponent: AppComponent
    }
}
package com.itechart.covid_tracker.app_level.dagger

import android.app.Application
import com.itechart.covid_tracker.app_level.dagger.component.*
import com.itechart.covid_tracker.app_level.dagger.module.AppModule
import com.itechart.covid_tracker.app_level.dagger.module.DBModule

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .dBModule(DBModule(applicationContext))
                .appModule(AppModule(applicationContext))
                .build()
    }

    companion object{
        lateinit var appComponent: AppComponent
    }
}
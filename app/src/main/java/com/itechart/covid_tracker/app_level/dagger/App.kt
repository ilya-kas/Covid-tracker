package com.itechart.covid_tracker.app_level.dagger

import android.app.Application
import com.itechart.covid_tracker.app_level.dagger.component.*
import com.itechart.covid_tracker.app_level.dagger.component.screen.DaggerProfileScreenComponent
import com.itechart.covid_tracker.app_level.dagger.component.screen.DaggerSettingsScreenComponent
import com.itechart.covid_tracker.app_level.dagger.component.screen.ProfileScreenComponent
import com.itechart.covid_tracker.app_level.dagger.component.screen.SettingsScreenComponent
import com.itechart.covid_tracker.app_level.dagger.module.DBModule

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .dBModule(DBModule(applicationContext))
                .build()

        screensComponent = DaggerScreensComponent.builder()
            .appComponent(appComponent)
            .build()

        settingsScreenComponent = DaggerSettingsScreenComponent.builder()
            .screensComponent(screensComponent)
            .build()

        profileScreenComponent = DaggerProfileScreenComponent.builder()
            .screensComponent(screensComponent)
            .build()
    }

    companion object{
        lateinit var appComponent: AppComponent
        lateinit var screensComponent: ScreensComponent
        lateinit var settingsScreenComponent: SettingsScreenComponent
        lateinit var profileScreenComponent: ProfileScreenComponent
    }
}
package com.itechart.myapplication.di

import com.itechart.covid_tracker.app_level.dagger.component.AppComponent
import com.itechart.covid_tracker.app_level.dagger.module.APIModule
import com.itechart.covid_tracker.app_level.dagger.module.DBModule
import com.itechart.myapplication.SettingsTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DBModule::class, APIModule::class])
interface TestComponent: AppComponent {
    fun inject(settingsTest: SettingsTest)
}
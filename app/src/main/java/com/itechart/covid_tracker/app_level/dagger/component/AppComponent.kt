package com.itechart.covid_tracker.app_level.dagger.component

import com.itechart.covid_tracker.app_level.dagger.module.APIModule
import com.itechart.covid_tracker.app_level.dagger.module.DBModule
import com.itechart.covid_tracker.app_level.dagger.module.ViewModelModule
import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.screens.settings.SettingsFragment
import com.itechart.covid_tracker.screens.settings.SettingsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DBModule::class, APIModule::class])
interface AppComponent {
    fun getModel(): Model
}
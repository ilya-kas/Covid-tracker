package com.itechart.covid_tracker.app_level.dagger

import com.itechart.covid_tracker.app_level.dagger.module.APIModule
import com.itechart.covid_tracker.app_level.dagger.module.DBModule
import com.itechart.covid_tracker.app_level.dagger.module.ViewModelModule
import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.screens.settings.SettingsFragment
import com.itechart.covid_tracker.screens.settings.SettingsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DBModule::class, APIModule::class, ViewModelModule::class])
interface AppComponent {
    fun getSettingsViewModel(): SettingsViewModel
    fun getModel(): Model

    fun injectSettingsFragment(fragment: SettingsFragment)
}
package com.itechart.covid_tracker.app_level.dagger.component

import com.itechart.covid_tracker.app_level.dagger.annotation.ScreenScope
import com.itechart.covid_tracker.app_level.dagger.module.ViewModelModule
import com.itechart.covid_tracker.screens.profile.ProfileViewModel
import com.itechart.covid_tracker.screens.settings.SettingsViewModel
import dagger.Component

@ScreenScope
@Component(dependencies = [AppComponent::class], modules = [ViewModelModule::class])
interface ScreensComponent {
    fun getSettingsViewModel(): SettingsViewModel
    fun getProfileViewModel(): ProfileViewModel
}
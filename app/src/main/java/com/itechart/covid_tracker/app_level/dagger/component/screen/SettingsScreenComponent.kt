package com.itechart.covid_tracker.app_level.dagger.component.screen

import com.itechart.covid_tracker.app_level.dagger.annotation.FragmentScope
import com.itechart.covid_tracker.app_level.dagger.component.ScreensComponent
import com.itechart.covid_tracker.screens.settings.SettingsFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ScreensComponent::class])
interface SettingsScreenComponent {
    fun injectSettingsFragment(fragment: SettingsFragment)
}
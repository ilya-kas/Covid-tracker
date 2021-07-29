package com.itechart.covid_tracker.app_level.dagger.component.screen

import com.itechart.covid_tracker.app_level.dagger.annotation.FragmentScope
import com.itechart.covid_tracker.app_level.dagger.component.ScreensComponent
import com.itechart.covid_tracker.screens.profile.ProfileFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ScreensComponent::class])
interface ProfileScreenComponent {
    fun injectProfileFragment(fragment: ProfileFragment)
}
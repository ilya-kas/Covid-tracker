package com.itechart.covid_tracker.app_level.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itechart.covid_tracker.app_level.dagger.ViewModelFactory
import com.itechart.covid_tracker.app_level.dagger.annotation.ViewModelKey
import com.itechart.covid_tracker.screens.profile.ProfileViewModel
import com.itechart.covid_tracker.screens.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds //points that result is the argument value. Instance of this value is created by dagger automatically
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun settingsViewModel(viewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun profileViewModel(viewModel: ProfileViewModel): ViewModel
}
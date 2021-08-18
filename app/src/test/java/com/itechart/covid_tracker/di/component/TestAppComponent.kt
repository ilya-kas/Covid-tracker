package com.itechart.covid_tracker.di.component

import com.itechart.covid_tracker.FavoritesViewModelTest
import com.itechart.covid_tracker.MainViewModelTest
import com.itechart.covid_tracker.app_level.dagger.component.screen.SettingsScreenComponent
import com.itechart.covid_tracker.app_level.dagger.module.APIModule
import com.itechart.covid_tracker.app_level.dagger.module.DBModule
import com.itechart.covid_tracker.SettingsViewModelTest
import com.itechart.covid_tracker.app_level.dagger.component.AppComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DBModule::class, APIModule::class])
interface TestAppComponent: AppComponent {
    fun inject(test: SettingsViewModelTest)
    fun inject(test: FavoritesViewModelTest)
    fun inject(test: MainViewModelTest)
}
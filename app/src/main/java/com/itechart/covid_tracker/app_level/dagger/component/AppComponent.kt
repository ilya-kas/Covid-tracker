package com.itechart.covid_tracker.app_level.dagger.component

import androidx.navigation.NavController
import com.itechart.covid_tracker.app_level.dagger.module.*
import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.screens.chart.ChartFragment
import com.itechart.covid_tracker.screens.favorites.view.FavoritesFragment
import com.itechart.covid_tracker.screens.chart.UpdateWorker
import com.itechart.covid_tracker.screens.main.view.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DBModule::class, APIModule::class, BindModule::class, NavigationModule::class])
interface AppComponent {
    fun getModel(): Model
    fun getNavigation(): NavController

    fun inject(fragment: MainFragment)
    fun inject(fragment: FavoritesFragment)
    fun inject(fragment: ChartFragment)

    fun inject(worker: UpdateWorker)
}
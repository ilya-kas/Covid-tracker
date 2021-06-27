package com.itechart.covid_tracker.app_level.dagger

import com.itechart.covid_tracker.model.database.favorites.FavoritesDAO
import com.itechart.covid_tracker.model.database.settings.SettingsDAO
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun getFavoritesDAO(): FavoritesDAO
    fun getSettingsDAO(): SettingsDAO
}
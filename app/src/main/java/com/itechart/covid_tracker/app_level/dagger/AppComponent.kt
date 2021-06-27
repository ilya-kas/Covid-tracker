package com.itechart.covid_tracker.app_level.dagger

import com.itechart.covid_tracker.model.database.FavoritesDAO
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun getFavoritesDAO(): FavoritesDAO
}
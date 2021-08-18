package com.itechart.covid_tracker.di.module

import android.content.Context
import com.itechart.covid_tracker.app_level.dagger.module.DBModule
import com.itechart.covid_tracker.model.database.favorites.FavoritesDAO
import com.itechart.covid_tracker.model.database.settings.SettingsDAO
import io.mockk.mockk

class TestDBModule(context: Context): DBModule(context) {
    override fun getFavoritesDAO(): FavoritesDAO = mockk()
    override fun getSettingsDAO(): SettingsDAO = mockk()
}
package com.itechart.covid_tracker.app_level.dagger.module

import android.content.Context
import androidx.room.Room
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.model.database.favorites.FavoritesDAO
import com.itechart.covid_tracker.model.database.favorites.FavoritesDatabase
import com.itechart.covid_tracker.model.database.settings.SettingsDAO
import com.itechart.covid_tracker.model.database.settings.SettingsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DBModule(val context: Context) {
    @Provides
    open fun getFavoritesDAO(): FavoritesDAO {
        val db = Room
            .databaseBuilder(context, FavoritesDatabase::class.java, "Favorites")
            .build() //accessing favorites database
        return db.countriesDao()
    }

    @Provides
    open fun getSettingsDAO(): SettingsDAO {
        val db = Room
            .databaseBuilder(context, SettingsDatabase::class.java, "Settings")
            .build() //accessing favorites database
        return db.settingsDao()
    }
}
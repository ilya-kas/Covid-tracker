package com.itechart.covid_tracker.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itechart.covid_tracker.model.database.favorites.FavoritesDAO
import com.itechart.covid_tracker.model.database.favorites.LoadableCountry
import com.itechart.covid_tracker.model.database.settings.LoadableSettings
import com.itechart.covid_tracker.model.database.settings.SettingsDAO

@Database(entities = [LoadableCountry::class, LoadableSettings::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun countriesDao(): FavoritesDAO
    abstract fun settingsDao(): SettingsDAO
}
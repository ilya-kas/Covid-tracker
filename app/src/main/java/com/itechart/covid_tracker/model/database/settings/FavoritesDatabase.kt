package com.itechart.covid_tracker.model.database.settings

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoadableSettings::class], version = 1)
abstract class SettingsDatabase : RoomDatabase() {
    abstract fun settingsDao(): SettingsDAO
}
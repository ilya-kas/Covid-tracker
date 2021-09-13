package com.itechart.covid_tracker.model.database.favorites

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoadableCountry::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun countriesDao(): FavoritesDAO
}
package com.itechart.covid_tracker.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itechart.covid_tracker.model.entities.Country

@Database(entities = [Country::class], version = 1)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun countriesDao(): FavoritesDAO
}
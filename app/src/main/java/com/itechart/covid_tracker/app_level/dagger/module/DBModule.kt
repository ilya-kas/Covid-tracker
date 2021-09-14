package com.itechart.covid_tracker.app_level.dagger.module

import android.content.Context
import androidx.room.Room
import com.itechart.covid_tracker.model.database.favorites.FavoritesDAO
import com.itechart.covid_tracker.model.database.Database
import com.itechart.covid_tracker.model.database.settings.SettingsDAO
import dagger.Module
import dagger.Provides

@Module
open class DBModule(val context: Context) {
    @Provides
    open fun getFavoritesDAO(): FavoritesDAO {
        val db = Room
            .databaseBuilder(context, Database::class.java, "Favorites")
            .build() //accessing favorites database
        return db.countriesDao()
    }

    @Provides
    open fun getSettingsDAO(): SettingsDAO {
        val db = Room
            .databaseBuilder(context, Database::class.java, "Settings")
            .build() //accessing favorites database
        return db.settingsDao()
    }
}
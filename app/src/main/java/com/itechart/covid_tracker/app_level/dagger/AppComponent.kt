package com.itechart.covid_tracker.app_level.dagger

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.itechart.covid_tracker.app_level.MainActivity
import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.model.database.favorites.FavoritesDAO
import com.itechart.covid_tracker.model.database.favorites.FavoritesDatabase
import com.itechart.covid_tracker.model.database.settings.SettingsDAO
import com.itechart.covid_tracker.model.database.settings.SettingsDatabase
import com.itechart.covid_tracker.model.entities.Settings
import com.itechart.covid_tracker.screens.settings.SettingsFragment
import com.itechart.covid_tracker.screens.settings.SettingsViewModel
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [DBModule::class])
interface AppComponent {
    fun getFavoritesDAO(): FavoritesDAO
    fun getSettingsDAO(): SettingsDAO
    fun getSettingsViewModel(): SettingsViewModel

    fun injectSettingsFragment(fragment: SettingsFragment)
}

var activity: MainActivity? = null //for var 1 injection

@Module
class DBModule(val context: Context) {
    @Provides
    fun getFavoritesDAO(): FavoritesDAO {
        val db = Room
            .databaseBuilder(context, FavoritesDatabase::class.java, "Favorites")
            .build() //accessing favorites database
        return db.countriesDao()
    }

    @Provides
    fun getSettingsDAO(): SettingsDAO {
        val db = Room
            .databaseBuilder(context, SettingsDatabase::class.java, "Settings")
            .build() //accessing favorites database
        return db.settingsDao()
    }

    //variant 1
    //@Provides
    //fun getSettingsViewModel(): SettingsViewModel {
    //    return ViewModelProvider(activity!!).get(SettingsViewModel::class.java)
    //}
}
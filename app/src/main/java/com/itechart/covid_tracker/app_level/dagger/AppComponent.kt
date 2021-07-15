package com.itechart.covid_tracker.app_level.dagger

import android.content.Context
import androidx.lifecycle.ViewModel
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
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Singleton
@Component(modules = [DBModule::class, ViewModelModule::class])
interface AppComponent {
    fun getFavoritesDAO(): FavoritesDAO
    fun getSettingsDAO(): SettingsDAO
    fun getSettingsViewModel(): SettingsViewModel
    fun getModel(): Model

    fun injectSettingsFragment(fragment: SettingsFragment)
}

//var activity: MainActivity? = null //for var 1 injection

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

    @Singleton
    @Provides
    fun getModel(): Model{
        return Model()
    }
}

@Module
interface ViewModelModule {
    @Binds //points that result is the argument value. Instance of this value is created by dagger automatically
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun settingsViewModel(viewModel: SettingsViewModel): ViewModel
}
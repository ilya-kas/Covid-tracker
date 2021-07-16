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
import com.itechart.covid_tracker.model.network.BASE_URL
import com.itechart.covid_tracker.model.network.CovidAPI
import com.itechart.covid_tracker.screens.settings.SettingsFragment
import com.itechart.covid_tracker.screens.settings.SettingsViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [DBModule::class, APIModule::class, ViewModelModule::class])
interface AppComponent {
    fun getSettingsViewModel(): SettingsViewModel
    fun getModel(): Model

    fun injectSettingsFragment(fragment: SettingsFragment)
    fun injectModel(model: Model)
}

/**
 * Modules
 */

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
        val model = Model()
        App.appComponent.injectModel(model)
        return model
    }
}

@Module
class APIModule{
    @Singleton
    @Provides
    fun getCovidAPI(): CovidAPI{
        val retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
        return retrofit.create(CovidAPI::class.java)
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
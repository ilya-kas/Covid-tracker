package com.itechart.covid_tracker.model

import com.itechart.covid_tracker.model.database.favorites.FavoritesProvider
import com.itechart.covid_tracker.model.database.settings.SettingsProvider
import com.itechart.covid_tracker.model.entities.Country
import com.itechart.covid_tracker.model.entities.Day
import com.itechart.covid_tracker.model.entities.Settings
import com.itechart.covid_tracker.model.entities.User
import com.itechart.covid_tracker.model.network.CovidStatsProvider
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class Model @Inject constructor(val favoritesProvider: FavoritesProvider,
                                val covidStatsProvider: CovidStatsProvider,
                                val settingsProvider: SettingsProvider){

    var countries:List<Country> = ArrayList(227) //227 is because api always returns this number
    var settings = Settings(true)

    /**
     * Favorites database methods
     */
    fun loadFavorites(){ //favorite countries loading from DB
        if (countries.isEmpty()) return
        val favorites = favoritesProvider.loadFavorites()
        for (country in favorites)
            countries[country.id].favorite = true
    }

    fun starred(country: Country){
        favoritesProvider.starred(country)
    }

    /**
     * settings database methods
     */

    fun loadSettings() {
        settings = settingsProvider.loadSettings()
    }

    fun saveSettings(settings: Settings){
        settingsProvider.save(settings)
    }

    /**
     * Retrofit data gathering methods
     */
    suspend fun loadCountries(){
        countries = covidStatsProvider.loadCountries()
    }

    suspend fun loadDays(nom:Int):List<Day>{
        return covidStatsProvider.loadDays(countries[nom])
    }

    /**
     * others
     */
    fun getDays(nom:Int):List<Day>{
        return (countries[nom].daysInfo!!)
    }

    fun loadUser(): User { //todo
        return User()
    }
}
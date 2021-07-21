package com.itechart.covid_tracker.model

import com.itechart.covid_tracker.model.database.favorites.FavoritesRepository
import com.itechart.covid_tracker.model.database.settings.SettingsRepository
import com.itechart.covid_tracker.model.entities.Country
import com.itechart.covid_tracker.model.entities.Day
import com.itechart.covid_tracker.model.entities.Settings
import com.itechart.covid_tracker.model.entities.User
import com.itechart.covid_tracker.model.network.CovidApiRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class Model @Inject constructor(val favoritesRepository: FavoritesRepository,
                                val covidApiRepository: CovidApiRepository,
                                val settingsRepository: SettingsRepository){

    var countries:List<Country> = ArrayList(227) //227 is because api always returns this number
    var settings = Settings(true)

    /**
     * Favorites database methods
     */
    fun loadFavorites(){ //favorite countries loading from DB
        if (countries.isEmpty()) return
        favoritesRepository.loadFavorites(countries)
    }

    fun starred(country: Country){
        favoritesRepository.starred(country)
    }

    /**
     * settings database methods
     */

    fun loadSettings() {
        settings = settingsRepository.loadSettings()
    }

    fun saveSettings(settings: Settings){
        settingsRepository.save(settings)
    }

    /**
     * Retrofit data gathering methods
     */
    suspend fun loadCountries(){
        countries = covidApiRepository.loadCountries()
    }

    suspend fun loadDays(nom:Int):List<Day>{
        return covidApiRepository.loadDays(countries[nom])
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
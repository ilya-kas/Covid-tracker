package com.itechart.covid_tracker.model

import com.itechart.covid_tracker.model.database.favorites.FavoritesRepository
import com.itechart.covid_tracker.model.database.settings.SettingsRepository
import com.itechart.covid_tracker.model.entities.Country
import com.itechart.covid_tracker.model.entities.Day
import com.itechart.covid_tracker.model.entities.Settings
import com.itechart.covid_tracker.model.entities.User
import com.itechart.covid_tracker.model.network.CovidApiRepository
import kotlin.collections.ArrayList

object Model {
    private lateinit var favoritesRepository: FavoritesRepository
    private lateinit var covidApiRepository: CovidApiRepository
    private lateinit var settingsRepository: SettingsRepository
    var countries:List<Country> = ArrayList(227) //227 is because api always returns this number
    var settings = Settings(true)

    fun initModel() {
        favoritesRepository = FavoritesRepository()
        covidApiRepository = CovidApiRepository()
        settingsRepository = SettingsRepository()
    }

    /**
     * Favorites database methods
     */
    fun loadFavorites(){ //favorite countries loading from DB
        favoritesRepository.loadFavorites(countries)
    }

    fun starred(country: Country){
        favoritesRepository.starred(country)
    }

    /**
     * Favorites database methods
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
package com.itechart.covid_tracker.model.database.favorites

import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.entities.Country
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoritesRepository {
    var favoritesDAO = App.appComponent.getFavoritesDAO() //DAO for ROOM DB access

    fun loadFavorites(countries: List<Country>){ //favorite countries loading from DB
        val favorites = favoritesDAO.getAll()
        for (country in favorites)
            countries[country.id].favorite = true
    }

    fun starred(country: Country){
        GlobalScope.launch {
            if (country.favorite)
                favoritesDAO.insert(LoadableCountry(country))
            else
                favoritesDAO.delete(LoadableCountry(country))
        }
    }
}
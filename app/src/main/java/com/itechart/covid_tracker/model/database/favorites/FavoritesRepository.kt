package com.itechart.covid_tracker.model.database.favorites

import com.itechart.covid_tracker.model.entities.Country
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepository @Inject constructor(var favoritesDAO: FavoritesDAO): FavoritesProvider{

    override fun loadFavorites(countries: List<Country>){
        val favorites = favoritesDAO.getAll()
        for (country in favorites)
            countries[country.id].favorite = true
    }

    override fun starred(country: Country){
        GlobalScope.launch {
            if (country.favorite)
                favoritesDAO.insert(LoadableCountry(country))
            else
                favoritesDAO.delete(LoadableCountry(country))
        }
    }
}
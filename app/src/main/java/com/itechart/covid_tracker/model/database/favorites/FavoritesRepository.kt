package com.itechart.covid_tracker.model.database.favorites

import com.itechart.covid_tracker.model.entities.Country
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesRepository @Inject constructor(var favoritesDAO: FavoritesDAO): FavoritesProvider{

    override fun loadFavorites() = favoritesDAO.getAll()

    override fun starred(country: Country){
        GlobalScope.launch {
            if (country.favorite)
                favoritesDAO.insert(LoadableCountry(country))
            else
                favoritesDAO.delete(LoadableCountry(country))
        }
    }
}
package com.itechart.covid_tracker.model.database.favorites

import com.itechart.covid_tracker.logic.entity.Country
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepository @Inject constructor(var favoritesDAO: FavoritesDAO): FavoritesProvider{

    override fun getFavorites(): List<Country> {
        val favorites = favoritesDAO.getAll()
        val result = LinkedList<Country>()
        for (country in favorites)
            result += Country(country.id, country.name, country.favorite, null)
        return result
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
package com.itechart.covid_tracker.model

import android.util.Log
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.database.FavoritesDAO
import com.itechart.covid_tracker.model.database.LoadableCountry
import com.itechart.covid_tracker.model.entities.Country
import com.itechart.covid_tracker.model.entities.Day
import com.itechart.covid_tracker.model.entities.Settings
import com.itechart.covid_tracker.model.entities.User
import com.itechart.covid_tracker.model.network.GetCountries
import com.itechart.covid_tracker.model.network.RetrofitInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

object Model {
    private val settings = Settings()
    lateinit var favoritesDAO: FavoritesDAO //DAO for ROOM DB access
    var countriesList: GetCountries? = null
    val countries = ArrayList<Country>(227) //227 is because api always returns this number

    fun initRoom() {
        favoritesDAO = App.appComponent.getFavoritesDAO()
    }

    fun loadFavorites(){ //favorite countries loading from DB
        val favorites = favoritesDAO.getAll()
        for (x in favorites)
            countries[x.id].favorite = true
    }

    fun starred(country: Country){
        GlobalScope.launch {
            if (country.favorite)
                favoritesDAO.insert(LoadableCountry(country))
            else
                favoritesDAO.delete(LoadableCountry(country))
        }
    }

    suspend fun loadCountries(){
        try {
            val response = RetrofitInstance.api.getCountiesList().awaitResponse()
            if (response.isSuccessful) {
                countriesList = response.body()

                countries.clear()
                for ((i, x) in countriesList!!.response.withIndex())
                    countries += Country(i, x, false, null)
            }
        } catch (e: IOException) {
            Log.e("Network", e.toString())
        } catch (e: HttpException){
            Log.e("Network", e.toString())
        }
    }

    fun getDays(nom:Int):List<Day>{
        return (countries[nom].info!!)
    }

    suspend fun loadDays(nom:Int):List<Day>{
        val list = LinkedList<Day>()
        try {
            val response = RetrofitInstance.api.getCountyStat(countries[nom].name).awaitResponse()
            if (response.isSuccessful) {
                val countryStatistics = response.body()

                for (body in countryStatistics!!.response)
                    body.cases.new.let { list += Day(body.day, body.cases.new.toInt())}
                list.reverse()
            }
        } catch (e: IOException) {
            Log.e("Network", e.toString())
        } catch (e: HttpException){
            Log.e("Network", e.toString())
        }

        countries[nom].info = list
        return list
    }

    fun loadUser(): User { //todo
        return User()
    }

    fun loadSettings(): Settings { //todo
        return settings
    }
}
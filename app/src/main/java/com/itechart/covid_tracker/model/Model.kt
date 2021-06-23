package com.itechart.covid_tracker.model

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.itechart.covid_tracker.model.database.FavoritesDAO
import com.itechart.covid_tracker.model.database.FavoritesDatabase
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
import java.time.MonthDay

object Model {
    private val settings = Settings()
    private var favoritesDAO: FavoritesDAO? = null //DAO for ROOM DB access
    var countriesList: GetCountries? = null
    val countries = ArrayList<Country>(227) //227 is because api always returns this number

    fun initRoom(context: Context) {
        val db = Room
            .databaseBuilder(context, FavoritesDatabase::class.java, "Favorites")
            .build() //accessing favorites database
        favoritesDAO = db.countriesDao()
    }

    fun loadFavorites(){ //favorite countries loading from DB
        val favorites = favoritesDAO!!.getAll()
        for (x in favorites)
            countries[x.id].favorite = true
    }

    fun starred(country: Country){
        GlobalScope.launch {
            if (country.favorite)
                favoritesDAO?.insert(LoadableCountry(country))
            else
                favoritesDAO?.delete(LoadableCountry(country))
        }
    }

    suspend fun loadCountries(){
        try {
            val response = RetrofitInstance.api.getCountiesList().awaitResponse()
            if (response.isSuccessful) {
                countriesList = response.body()

                countries.clear()
                for ((i, x) in countriesList!!.response.withIndex()) {
                    countries += Country(i, x, false, null)
                }
            }
        } catch (e: IOException) {
            Log.e("Network", e.toString())
        } catch (e: HttpException){
            Log.e("Network", e.toString())
        }
    }

    fun loadDays(nom:Int):ArrayList<Day>{ //todo
        val list = ArrayList<Day>()
        when(nom){
            0 -> {
                list += Day(MonthDay.now(), 2)
                list += Day(MonthDay.now(),3)
                list += Day(MonthDay.now(),-14)
                list += Day(MonthDay.now(),8)
                list += Day(MonthDay.now(),-14)
                list += Day(MonthDay.now(),8)
                list += Day(MonthDay.now(), 2)
                list += Day(MonthDay.now(),3)
                list += Day(MonthDay.now(),-14)
                list += Day(MonthDay.now(), 2)
                list += Day(MonthDay.now(),3)
            }
            1 -> {
                list += Day(MonthDay.now(), 2)
                list += Day(MonthDay.now(),3)
                list += Day(MonthDay.now(),-14)
                list += Day(MonthDay.now(),8)
                list += Day(MonthDay.now(),-14)
                list += Day(MonthDay.now(),8)
                list += Day(MonthDay.now(), 2)
                list += Day(MonthDay.now(),3)
            }
            2 -> {
                list += Day(MonthDay.now(),3)
            }
        }

        return list
    }

    fun loadUser(): User { //todo
        return User()
    }

    fun loadSettings(): Settings { //todo
        return settings
    }
}
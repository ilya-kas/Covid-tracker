package com.itechart.covid_tracker.model.network

import android.util.Log
import androidx.annotation.Keep
import com.itechart.covid_tracker.model.entities.Country
import com.itechart.covid_tracker.model.entities.Day
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.IOException
import java.lang.Exception
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class CovidApiRepository @Inject constructor(val api: CovidAPI): CovidStatsProvider {
    val countries = ArrayList<Country>()
    private var days: List<Day> = ArrayList()
    private var loadedDayNom = -1

    override suspend fun loadCountries(): List<Country>{
        countries.clear()
        try {
            val response = api.getCountiesList().awaitResponse()
            if (response.isSuccessful) {
                val countriesList = response.body()

                for ((i, x) in countriesList!!.response.withIndex())
                    countries += Country(i, x, false, null)
            }
        } catch (e: IOException) {
            Log.e("Network", e.toString())
        } catch (e: HttpException){
            Log.e("Network", e.toString())
        }
        return countries
    }

    override suspend fun preloadDays(nom: Int){
        val list = LinkedList<Day>()
        try {
            val response = api.getCountyStat(countries[nom].name).awaitResponse()
            if (response.isSuccessful) {
                val countryStatistics = response.body()

                for (body in countryStatistics!!.response)
                    try {
                        list += Day(body.day, body.cases.new.toInt())
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                list.reverse()
            }
        } catch (e: IOException) {
            Log.e("Network", e.toString())
        } catch (e: HttpException){
            Log.e("Network", e.toString())
        }

        countries[nom].daysInfo = list
        loadedDayNom = nom
        days = list
    }

    override fun loadDays(nom: Int): List<Day> {
        if (nom != loadedDayNom) //impossible state
            return ArrayList()
        return days
    }
}
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
import kotlin.collections.ArrayList

class CovidApiRepository @Inject constructor(val api: CovidAPI): CovidStatsProvider {

    override suspend fun loadCountries(): List<Country>{
        val result = ArrayList<Country>()

        try {
            val response = api.getCountiesList().awaitResponse()
            if (response.isSuccessful) {
                val countriesList = response.body()

                for ((i, x) in countriesList!!.response.withIndex())
                    result += Country(i, x, false, null)
            }
        } catch (e: IOException) {
            Log.e("Network", e.toString())
        } catch (e: HttpException){
            Log.e("Network", e.toString())
        }
        return result
    }

    override suspend fun loadDays(country: Country):List<Day>{
        val list = LinkedList<Day>()
        try {
            val response = api.getCountyStat(country.name).awaitResponse()
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

        country.daysInfo = list
        return list
    }
}
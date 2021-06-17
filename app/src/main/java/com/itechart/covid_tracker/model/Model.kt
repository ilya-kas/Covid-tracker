package com.itechart.covid_tracker.model

import com.itechart.covid_tracker.presenter.main.Country
import com.itechart.covid_tracker.presenter.chart.Day
import com.itechart.covid_tracker.presenter.profile.User
import java.time.MonthDay

object Model {
    private val countries = ArrayList<Country>()
    init {//todo tmp
        countries += Country().apply { name = "group 1" }
        countries += Country().apply { name = "group 2"; favorite = true }
        countries += Country().apply { name = "group 3" }
        countries += Country().apply { name = "group 4"; favorite = true }
        countries += Country().apply { name = "group 5" }
        countries += Country().apply { name = "group 6"; favorite = true }
        countries += Country().apply { name = "group 7" }
        countries += Country().apply { name = "group 8"; favorite = true }
        countries += Country().apply { name = "group 9" }
        countries += Country().apply { name = "group 10"; favorite = true }
        countries += Country().apply { name = "group 11" }
        countries += Country().apply { name = "group 12"; favorite = true }
        countries += Country().apply { name = "group 13" }
    }

    fun loadCountries():ArrayList<Country>{ //todo
        return countries
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
        val user = User()
        return user
    }
}
package com.itechart.covid_tracker.model

import com.itechart.covid_tracker.presenter.main.Country
import com.itechart.covid_tracker.presenter.chart.Day
import com.itechart.covid_tracker.presenter.profile.User

object Model {
    fun loadGroups():ArrayList<Country>{ //todo tmp
        val list = ArrayList<Country>()
        list += Country().apply { name = "group 1" }
        list += Country().apply { name = "group 2"; favorite = true }
        list += Country().apply { name = "group 3" }
        list += Country().apply { name = "group 4"; favorite = true }
        list += Country().apply { name = "group 5" }
        list += Country().apply { name = "group 6"; favorite = true }
        list += Country().apply { name = "group 7" }
        list += Country().apply { name = "group 8"; favorite = true }
        list += Country().apply { name = "group 9" }
        list += Country().apply { name = "group 10"; favorite = true }
        list += Country().apply { name = "group 11" }
        list += Country().apply { name = "group 12"; favorite = true }
        list += Country().apply { name = "group 13" }

        return list
    }

    fun loadItems(nom:Int):ArrayList<Day>{ //todo tmp
        val list = ArrayList<Day>()
        when(nom){
            0 -> {
                list += Day().apply { text = "long 1"; shortText = "1" }
                list += Day().apply { text = "long 2"; shortText = "2" }
                list += Day().apply { text = "long 3"; shortText = "3" }
                list += Day().apply { text = "long 4"; shortText = "4" }
                list += Day().apply { text = "long 5"; shortText = "5" }
                list += Day().apply { text = "long 6"; shortText = "6" }
                list += Day().apply { text = "long 7"; shortText = "7" }
                list += Day().apply { text = "long 8"; shortText = "8" }
                list += Day().apply { text = "long 9"; shortText = "9" }
                list += Day().apply { text = "long 10"; shortText = "10" }
                list += Day().apply { text = "long 11"; shortText = "11" }
            }
            1 -> {
                list += Day().apply { text = "long 1"; shortText = "1" }
                list += Day().apply { text = "long 2"; shortText = "2" }
                list += Day().apply { text = "long 3"; shortText = "3" }
                list += Day().apply { text = "long 4"; shortText = "4" }
                list += Day().apply { text = "long 5"; shortText = "5" }
                list += Day().apply { text = "long 6"; shortText = "6" }
                list += Day().apply { text = "long 7"; shortText = "7" }
                list += Day().apply { text = "long 8"; shortText = "8" }
            }
            2 -> {
                list += Day().apply { text = "long 1"; shortText = "1" }
            }
            3 -> {
            }
        }

        return list
    }

    fun loadUser(): User {
        val user = User()
        return user
    }
}
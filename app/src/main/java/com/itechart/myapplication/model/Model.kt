package com.itechart.myapplication.model

import com.itechart.myapplication.presenter.main.Group
import com.itechart.myapplication.presenter.chart.Item
import com.itechart.myapplication.presenter.profile.User

object Model {
    fun loadGroups():ArrayList<Group>{ //todo tmp
        val list = ArrayList<Group>()
        list += Group().apply { name = "group 1" }
        list += Group().apply { name = "group 2"; favorite = true }
        list += Group().apply { name = "group 3" }
        list += Group().apply { name = "group 4"; favorite = true }
        list += Group().apply { name = "group 5" }
        list += Group().apply { name = "group 6"; favorite = true }
        list += Group().apply { name = "group 7" }
        list += Group().apply { name = "group 8"; favorite = true }
        list += Group().apply { name = "group 9" }
        list += Group().apply { name = "group 10"; favorite = true }
        list += Group().apply { name = "group 11" }
        list += Group().apply { name = "group 12"; favorite = true }
        list += Group().apply { name = "group 13" }

        return list
    }

    fun loadItems(nom:Int):ArrayList<Item>{ //todo tmp
        val list = ArrayList<Item>()
        when(nom){
            0 -> {
                list += Item().apply { text = "long 1"; shortText = "1" }
                list += Item().apply { text = "long 2"; shortText = "2" }
                list += Item().apply { text = "long 3"; shortText = "3" }
                list += Item().apply { text = "long 4"; shortText = "4" }
                list += Item().apply { text = "long 5"; shortText = "5" }
                list += Item().apply { text = "long 6"; shortText = "6" }
                list += Item().apply { text = "long 7"; shortText = "7" }
                list += Item().apply { text = "long 8"; shortText = "8" }
                list += Item().apply { text = "long 9"; shortText = "9" }
                list += Item().apply { text = "long 10"; shortText = "10" }
                list += Item().apply { text = "long 11"; shortText = "11" }
            }
            1 -> {
                list += Item().apply { text = "long 1"; shortText = "1" }
                list += Item().apply { text = "long 2"; shortText = "2" }
                list += Item().apply { text = "long 3"; shortText = "3" }
                list += Item().apply { text = "long 4"; shortText = "4" }
                list += Item().apply { text = "long 5"; shortText = "5" }
                list += Item().apply { text = "long 6"; shortText = "6" }
                list += Item().apply { text = "long 7"; shortText = "7" }
                list += Item().apply { text = "long 8"; shortText = "8" }
            }
            2 -> {
                list += Item().apply { text = "long 1"; shortText = "1" }
            }
            3 -> {
            }
        }

        return list
    }

    fun loadUser():User{
        val user = User()
        return user
    }
}
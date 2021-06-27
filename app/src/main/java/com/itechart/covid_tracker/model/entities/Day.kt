package com.itechart.covid_tracker.model.entities

class Day(private val date:String, val count:Int) {
    val text
        get() = "$date: $count"
    val shortText
        get() = count.toString()
}
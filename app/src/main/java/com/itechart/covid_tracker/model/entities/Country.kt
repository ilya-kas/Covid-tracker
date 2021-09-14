package com.itechart.covid_tracker.model.entities

class Country(var id: Int, var name: String, var favorite: Boolean, info: List<Day>?) {
    var daysInfo: List<Day>? = info

}
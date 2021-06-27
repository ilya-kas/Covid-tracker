package com.itechart.covid_tracker.model.entities

import com.itechart.covid_tracker.model.Model

class Country{
    private var initialization = true //not to reset favorites value while creating list of countries

    var id: Int
    var name: String
    var favorite: Boolean
        set(value) {
            field = value

            if (initialization){
                initialization = false
                return
            }
            Model.starred(this)
        }
    var daysInfo: List<Day>?

    constructor(id: Int, name: String, favorite: Boolean, info: List<Day>?) {
        this.id = id
        this.name = name
        this.favorite = favorite
        this.daysInfo = info
    }
}
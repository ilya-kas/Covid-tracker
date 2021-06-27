package com.itechart.covid_tracker.model.database.favorites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.itechart.covid_tracker.model.entities.Country

@Entity(tableName = "favorites")
data class LoadableCountry (
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var name: String,
        var favorite: Boolean
){
        constructor(country: Country) : this(country.id, country.name, country.favorite)
}
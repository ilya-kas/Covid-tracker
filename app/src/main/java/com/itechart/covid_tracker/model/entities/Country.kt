package com.itechart.covid_tracker.model.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Country (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var favorite: Boolean
){
    @Ignore
    var info: List<Day>? = null

    constructor(id: Int,
                name: String,
                favorite: Boolean,
                info: List<Day>?) : this(id, name, favorite){
                    this.info = info
                }
}
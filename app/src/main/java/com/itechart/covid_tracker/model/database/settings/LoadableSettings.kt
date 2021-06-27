package com.itechart.covid_tracker.model.database.settings

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.itechart.covid_tracker.model.entities.Country
import com.itechart.covid_tracker.model.entities.Settings

@Entity(tableName = "settings")
data class LoadableSettings (
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var notifications: Boolean
){
        constructor(settings: Settings) : this(0, settings.notifications)
}
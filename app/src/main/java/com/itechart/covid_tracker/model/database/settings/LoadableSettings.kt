package com.itechart.covid_tracker.model.database.settings

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.itechart.covid_tracker.logic.entity.Settings

@Entity(tableName = "settings")
data class LoadableSettings (
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var notifications: Boolean,
        var firstLaunch: Boolean
){
        constructor(settings: Settings) : this(0, settings.notifications, settings.firstLaunch)
}
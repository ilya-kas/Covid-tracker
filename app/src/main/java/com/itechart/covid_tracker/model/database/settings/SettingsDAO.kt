package com.itechart.covid_tracker.model.database.settings

import androidx.room.*

@Dao
interface SettingsDAO {
    @Query("SELECT * FROM settings")
    fun getSettings(): List<LoadableSettings>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(settings: LoadableSettings)
}
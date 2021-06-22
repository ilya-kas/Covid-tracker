package com.itechart.covid_tracker.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itechart.covid_tracker.model.entities.Country

@Dao
interface FavoritesDAO {
    @Query("SELECT * FROM favorites ORDER BY name ASC")
    fun getAll(): List<Country>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: Country)
}
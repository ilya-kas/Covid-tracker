package com.itechart.covid_tracker.model.database

import androidx.room.*

@Dao
interface FavoritesDAO {
    @Query("SELECT * FROM favorites ORDER BY name ASC")
    fun getAll(): List<LoadableCountry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: LoadableCountry)

    @Delete
    fun delete(country: LoadableCountry)
}
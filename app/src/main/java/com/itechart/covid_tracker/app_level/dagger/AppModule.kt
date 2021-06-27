package com.itechart.covid_tracker.app_level.dagger

import androidx.room.Room
import com.itechart.covid_tracker.model.database.FavoritesDAO
import com.itechart.covid_tracker.model.database.FavoritesDatabase
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun getFavoritesDAO(): FavoritesDAO{
        //val db = Room
        //    .databaseBuilder(context, FavoritesDatabase::class.java, "Favorites")
        //    .build() //accessing favorites database
        //return db.countriesDao()
    }
}
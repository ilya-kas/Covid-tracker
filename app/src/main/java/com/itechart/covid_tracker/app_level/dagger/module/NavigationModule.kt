package com.itechart.covid_tracker.app_level.dagger.module

import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.app_level.MainActivity
import dagger.Module
import dagger.Provides

lateinit var storedActivity: MainActivity

@Module
open class NavigationModule() {
    @Provides
    fun getNavController(): NavController = Navigation.findNavController(storedActivity, R.id.fragment_container)
}
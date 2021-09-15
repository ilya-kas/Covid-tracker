package com.itechart.covid_tracker.app_level

import android.os.Handler
import androidx.core.view.get
import androidx.navigation.NavController
import com.google.android.material.navigationrail.NavigationRailView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.database.favorites.FavoritesProvider
import com.itechart.covid_tracker.model.network.CovidStatsProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuController(activity: MainActivity) {
    val top_menu = activity.findViewById<NavigationRailView>(R.id.top_navigation_rail)
    val bottom_menu = activity.findViewById<NavigationRailView>(R.id.bottom_navigation_rail)

    @Inject
    lateinit var favoritesProvider: FavoritesProvider
    @Inject
    lateinit var covidStatsProvider: CovidStatsProvider
    @Inject
    lateinit var navigation: NavController

    var flag = false // flag if item was selected by program

    init {
        App.appComponent.inject(this)

        bottom_menu.menu[0].isChecked = false
        top_menu.itemIconTintList = null
        bottom_menu.itemIconTintList = null

        top_menu.setOnItemSelectedListener{ item ->
            if (flag)
                return@setOnItemSelectedListener true
            flag = true
            bottom_menu.selectedItemId = R.id.hidden

            when(item.itemId) {
                R.id.main -> {
                    navigation.popBackStack()
                    navigation.navigate(R.id.mainFragment)
                }
                R.id.profile -> {
                    navigation.popBackStack()
                    navigation.navigate(R.id.profileFragment)
                }
                R.id.favorites -> {
                    GlobalScope.launch {
                        favoritesProvider.getFavorites() //updating favorites stats

                        val mainHandler = Handler(App.appComponent.getContext().mainLooper)
                        mainHandler.post {
                            navigation.popBackStack()
                            navigation.navigate(R.id.favoritesFragment)
                        }
                    }
                }
            }

            flag = false
            true
        }

        bottom_menu.setOnItemSelectedListener{ item ->
            if (flag)
                return@setOnItemSelectedListener true
            flag = true
            top_menu.selectedItemId = R.id.hidden

            when(item.itemId) {
                R.id.settings -> {
                    navigation.popBackStack()
                    navigation.navigate(R.id.settingsFragment)
                }
                R.id.enter -> {
                    navigation.popBackStack()
                    navigation.navigate(R.id.enterFragment)
                }
            }

            flag = false
            true
        }
    }
}
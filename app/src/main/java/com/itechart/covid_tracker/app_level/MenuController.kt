package com.itechart.covid_tracker.app_level

import androidx.core.view.get
import androidx.navigation.Navigation
import com.google.android.material.navigationrail.NavigationRailView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.screens.favorites.view.FavoritesFragment
import com.itechart.covid_tracker.screens.login.view.EnterFragment
import com.itechart.covid_tracker.screens.main.view.MainFragment
import com.itechart.covid_tracker.screens.profile.ProfileFragment
import com.itechart.covid_tracker.screens.settings.SettingsFragment

class MenuController(activity: MainActivity) {
    val top_menu = activity.findViewById<NavigationRailView>(R.id.top_navigation_rail)
    val bottom_menu = activity.findViewById<NavigationRailView>(R.id.bottom_navigation_rail)

    var flag = false // flag if item was selected by program

    init {
        val navigation = App.appComponent.getNavigation()

        bottom_menu.menu[0].isChecked = false
        top_menu.itemIconTintList = null
        bottom_menu.itemIconTintList = null

        top_menu.setOnItemSelectedListener{ item ->
            if (flag)
                return@setOnItemSelectedListener true
            flag = true
            bottom_menu.selectedItemId = R.id.hidden

            when(item.itemId) {
                R.id.main -> navigation.navigate(R.id.mainFragment)
                R.id.profile -> navigation.navigate(R.id.profileFragment)
                R.id.favorites -> navigation.navigate(R.id.favoritesFragment)
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
                R.id.settings -> navigation.navigate(R.id.settingsFragment)
                R.id.enter -> navigation.navigate(R.id.enterFragment)
            }

            flag = false
            true
        }
    }
}
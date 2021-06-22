package com.itechart.covid_tracker.activities

import androidx.core.view.get
import com.google.android.material.navigationrail.NavigationRailView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.screens.favorites.view.FavoritesFragment
import com.itechart.covid_tracker.screens.login.view.EnterFragment
import com.itechart.covid_tracker.screens.main.view.MainFragment
import com.itechart.covid_tracker.screens.profile.view.ProfileFragment
import com.itechart.covid_tracker.screens.settings.view.SettingsFragment

class MenuController(activity: MainActivity) {
    val top_menu = activity.findViewById<NavigationRailView>(R.id.top_navigation_rail)
    val bottom_menu = activity.findViewById<NavigationRailView>(R.id.bottom_navigation_rail)

    var flag = false // flag if item was selected by program

    init {
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
                    activity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, MainFragment.newInstance())
                            .commit()
                }
                R.id.profile -> {
                    activity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, ProfileFragment.newInstance())
                            .commit()
                }
                R.id.favorites -> {
                    activity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, FavoritesFragment.newInstance())
                            .commit()
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
                    activity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, SettingsFragment.newInstance())
                            .commit()
                }
                R.id.enter -> {
                    activity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, EnterFragment.newInstance())
                            .commit()
                }
            }

            flag = false
            true
        }
    }
}
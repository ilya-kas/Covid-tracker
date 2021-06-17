package com.itechart.covid_tracker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.navigationrail.NavigationRailView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.view.favorites_screen.FavoritesFragment
import com.itechart.covid_tracker.view.main_screen.MainFragment
import com.itechart.covid_tracker.view.profile_screen.ProfileFragment

class MainActivity : AppCompatActivity() {
    lateinit var menuController: MenuController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuController = MenuController(this)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, MainFragment.newInstance())
            .commit()
    }
}
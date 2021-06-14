package com.itechart.covid_tracker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.navigationrail.NavigationRailView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.presenter.main.MainPresenter
import com.itechart.covid_tracker.view.main_screen.MainFragment
import com.itechart.covid_tracker.view.profile_screen.ProfileFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainPresenter.fragmentManager = supportFragmentManager

        val menu = findViewById<NavigationRailView>(R.id.navigation_rail)
        menu.setOnItemSelectedListener{ item ->
            when(item.itemId) {
                R.id.main -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, MainFragment.newInstance())
                        .commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment.newInstance())
                        .commit()
                    true
                }
                R.id.favorites -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, MainFragment.newInstance()) //todo
                        .commit()
                    true
                }
                R.id.settings -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, MainFragment.newInstance()) //todo
                        .commit()
                    true
                }
                R.id.enter -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, MainFragment.newInstance()) //todo
                        .commit()
                    true
                }
                else -> false
            }
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, MainFragment.newInstance())
            .commit()
    }
}
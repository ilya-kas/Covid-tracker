package com.itechart.covid_tracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.screens.main.view.MainFragment

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
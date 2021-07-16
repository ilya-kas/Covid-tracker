package com.itechart.covid_tracker.app_level

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itechart.covid_tracker.R
//import com.itechart.covid_tracker.app_level.dagger.activity
import com.itechart.covid_tracker.app_level.notification.NotificationService
import com.itechart.covid_tracker.screens.main.view.MainFragment


class MainActivity : AppCompatActivity() {
    lateinit var menuController: MenuController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuController = MenuController(this)

        startService(Intent(this, NotificationService::class.java))

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, MainFragment.newInstance())
            .commit()
    }
}
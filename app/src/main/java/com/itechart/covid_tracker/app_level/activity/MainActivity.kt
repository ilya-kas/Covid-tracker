package com.itechart.covid_tracker.app_level.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.app_level.MenuController
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.app_level.dagger.module.storedActivity
import com.itechart.covid_tracker.app_level.notification.NotificationService
import com.itechart.covid_tracker.screens.main.view.MainFragment
import com.itechart.covid_tracker.screens.settings.SettingsViewModel
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainActivity @Inject constructor() : AppCompatActivity() {
    lateinit var menuController: MenuController

    @Inject
    lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.appComponent.inject(this)

        storedActivity = this
        menuController = MenuController(this)

        startService(Intent(this, NotificationService::class.java))

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .commit()

        if (settingsViewModel.settings.firstLaunch)
            guide(0)
    }

    private fun guide(step: Int){
        val text = when (step){
            0 -> "This is a list of countries"
            1 -> "You can search for a country here"
            2 -> "There is a menu with main, profile, favorites"
            3 -> "You can click at the star to add/remove country from favorites"
            4 -> "Click on a country item to see statistics about this country"
            else -> return
        }
        val target = when (step){
            0 -> findViewById(R.id.rv_countries)
            1 -> findViewById(R.id.et_search)
            2 -> findViewById(R.id.top_navigation_rail)
            3 -> findViewById(R.id.ib_star)
            else -> (findViewById<RecyclerView>(R.id.rv_countries))[0]
        }

        GuideView.Builder(this)
            .setContentText(text)
            .setTargetView(target)
            .setContentTextSize(12) //optional
            .setTitleTextSize(14) //optional
            .setDismissType(GuideView.DismissType.targetView) //optional - default dismissible by TargetView
            .setGuideListener {
                guide(step + 1)
            }
            .setDismissType(GuideView.DismissType.anywhere)
            .build()
            .show()
    }
}
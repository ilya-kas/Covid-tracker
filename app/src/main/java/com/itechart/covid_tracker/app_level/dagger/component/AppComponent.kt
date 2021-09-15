package com.itechart.covid_tracker.app_level.dagger.component

import android.content.Context
import androidx.navigation.NavController
import com.itechart.covid_tracker.app_level.MenuController
import com.itechart.covid_tracker.app_level.SplashScreenActivity
import com.itechart.covid_tracker.app_level.dagger.module.*
import com.itechart.covid_tracker.app_level.notification.NotificationService
import com.itechart.covid_tracker.screens.chart.ChartFragment
import com.itechart.covid_tracker.screens.favorites.view.FavoritesFragment
import com.itechart.covid_tracker.screens.chart.UpdateWorker
import com.itechart.covid_tracker.screens.login.view.EnterFragment
import com.itechart.covid_tracker.screens.login.view.LoginFragment
import com.itechart.covid_tracker.screens.login.view.RegistrationFragment
import com.itechart.covid_tracker.screens.main.view.MainFragment
import com.itechart.covid_tracker.screens.profile.ProfileFragment
import com.itechart.covid_tracker.screens.settings.SettingsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DBModule::class, APIModule::class, BindModule::class, AppModule::class])
interface AppComponent {
    fun getNavigation(): NavController
    fun getContext(): Context

    fun inject(activity: SplashScreenActivity)
    fun inject(fragment: MainFragment)
    fun inject(fragment: FavoritesFragment)
    fun inject(fragment: ChartFragment)
    fun inject(fragment: EnterFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: SettingsFragment)

    fun inject(worker: UpdateWorker)
    fun inject(service: NotificationService)
    fun inject(controller: MenuController)
}
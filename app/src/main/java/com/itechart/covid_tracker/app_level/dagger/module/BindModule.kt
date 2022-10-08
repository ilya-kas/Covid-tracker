package com.itechart.covid_tracker.app_level.dagger.module

import com.itechart.covid_tracker.model.FilledCountriesProvider
import com.itechart.covid_tracker.model.accounts.AccountsManager
import com.itechart.covid_tracker.model.accounts.FirebaseManager
import com.itechart.covid_tracker.model.database.favorites.FavoritesProvider
import com.itechart.covid_tracker.model.database.favorites.FavoritesRepository
import com.itechart.covid_tracker.model.database.settings.SettingsProvider
import com.itechart.covid_tracker.model.database.settings.SettingsRepository
import com.itechart.covid_tracker.model.network.CovidStatsProvider
import dagger.Binds
import dagger.Module

@Module
interface BindModule {
    @Binds
    fun bindCovidStatsProvider(repository: FilledCountriesProvider): CovidStatsProvider

    @Binds
    fun bindFavoritesProvider(repository: FavoritesRepository): FavoritesProvider

    @Binds
    fun bindSettingsProvider(repository: SettingsRepository): SettingsProvider

    @Binds
    fun bindAccountsManager(manager: FirebaseManager): AccountsManager
}
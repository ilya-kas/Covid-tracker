package com.itechart.covid_tracker.di.module

import com.itechart.covid_tracker.app_level.dagger.module.APIModule
import com.itechart.covid_tracker.model.network.CovidAPI
import io.mockk.mockk

class TestAPIModule: APIModule() {
    override fun getCovidAPI(): CovidAPI = mockk()
}
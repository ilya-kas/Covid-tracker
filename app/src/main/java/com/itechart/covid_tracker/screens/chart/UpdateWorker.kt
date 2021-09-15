package com.itechart.covid_tracker.screens.chart

import android.content.Context
import android.os.Handler
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.FilledCountriesProvider
import com.itechart.covid_tracker.model.network.CovidStatsProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpdateWorker(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams) {
    @Inject
    lateinit var viewModel: ChartViewModel
    @Inject
    lateinit var filledCountriesProvider: FilledCountriesProvider

    override fun doWork(): Result {
        App.appComponent.inject(this)

        GlobalScope.launch {
            val daysStats = filledCountriesProvider.loadDays(viewModel.number)

            val mainHandler = Handler(applicationContext.mainLooper)
            mainHandler.post {
                viewModel.days.value = daysStats
            }
        }

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }
}
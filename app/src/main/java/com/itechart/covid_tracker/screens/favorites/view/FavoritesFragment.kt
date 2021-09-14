package com.itechart.covid_tracker.screens.favorites.view

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.network.CovidStatsProvider
import com.itechart.covid_tracker.screens.chart.ChartFragment
import com.itechart.covid_tracker.screens.favorites.FavoritesViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesFragment: Fragment() {
    private lateinit var fragment:View
    @Inject
    lateinit var viewModel: FavoritesViewModel
    @Inject
    lateinit var covidStatsProvider: CovidStatsProvider

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_favorites, container, false)
        App.appComponent.inject(this)

        val recyclerView = fragment.findViewById<RecyclerView>(R.id.rv_countries)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecyclerAdapter(this, viewModel.countries)

        val mItemTouchHelper = ItemTouchHelper(ListTouchAdapter(recyclerView.adapter as RecyclerAdapter))
        mItemTouchHelper.attachToRecyclerView(recyclerView)

        return fragment
    }

    fun lineItemPressed(realPosition:Int){
        GlobalScope.launch {
            covidStatsProvider.preloadDays(realPosition)

            val navigation = App.appComponent.getNavigation()
            val mainHandler = Handler(App.appComponent.getContext().mainLooper)
            mainHandler.post {
                navigation.navigate(R.id.action_mainFragment_to_chartFragment)
            }
        }
    }

    fun itemRemoved(nom: Int){
        viewModel.itemRemoved(nom)
    }
}
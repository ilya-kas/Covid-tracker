package com.itechart.covid_tracker.screens.favorites.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.Model
import com.itechart.covid_tracker.screens.chart.ChartFragment
import com.itechart.covid_tracker.screens.chart.ChartViewModel
import com.itechart.covid_tracker.screens.favorites.FavoritesViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesFragment: Fragment() {
    private lateinit var fragment:View
    @Inject
    lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_favorites, container, false)
        App.appComponent.inject(this)

        val recyclerView = fragment.findViewById<RecyclerView>(R.id.rv_countries)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecyclerAdapter(this, viewModel)

        val mItemTouchHelper = ItemTouchHelper(ListTouchAdapter(recyclerView.adapter as RecyclerAdapter))
        mItemTouchHelper.attachToRecyclerView(recyclerView)

        return fragment
    }

    fun lineItemPressed(realPosition:Int){
        GlobalScope.launch {
            val model = App.appComponent.getModel()//todo
            model.loadDays(realPosition)
            parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, ChartFragment.newInstance(realPosition))
                    .addToBackStack("chart")
                    .commit()
        }
    }

    companion object{
        fun newInstance(): Fragment {
            return FavoritesFragment()
        }
    }
}
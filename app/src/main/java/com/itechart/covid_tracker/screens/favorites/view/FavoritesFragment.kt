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
import com.itechart.covid_tracker.screens.chart.ChartViewModel
import com.itechart.covid_tracker.screens.favorites.FavoritesViewModel

class FavoritesFragment: Fragment() {
    private lateinit var fragment:View
    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_favorites, container, false)
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)

        val recyclerView = fragment.findViewById<RecyclerView>(R.id.rv_countries)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecyclerAdapter(viewModel)

        val mItemTouchHelper = ItemTouchHelper(ListTouchAdapter(recyclerView.adapter as RecyclerAdapter))
        mItemTouchHelper.attachToRecyclerView(recyclerView)

        return fragment
    }

    companion object{
        fun newInstance(): Fragment {
            return FavoritesFragment()
        }
    }
}
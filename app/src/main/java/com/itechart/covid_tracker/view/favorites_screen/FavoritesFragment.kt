package com.itechart.covid_tracker.view.favorites_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.presenter.favorites.FavoritesPresenter
import com.itechart.covid_tracker.presenter.main.MainPresenter
import com.itechart.covid_tracker.view.chart_screen.ChartFragment
import kotlin.math.ceil

class FavoritesFragment: Fragment() {
    private lateinit var fragment:View
    private lateinit var presenter: FavoritesPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_favorites, container, false)
        presenter = FavoritesPresenter(this)

        val recyclerView = fragment.findViewById<RecyclerView>(R.id.rv_countries)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecyclerAdapter(presenter)

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
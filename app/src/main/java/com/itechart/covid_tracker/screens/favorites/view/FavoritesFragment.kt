package com.itechart.covid_tracker.screens.favorites.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.screens.favorites.presenter.FavoritesPresenter

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
package com.itechart.covid_tracker.view.main_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.presenter.main.MainPresenter
import com.itechart.covid_tracker.view.chart_screen.ChartFragment
import kotlin.math.ceil

class MainFragment: Fragment() {
    private lateinit var fragment:View
    private lateinit var presenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_main, container, false)
        presenter = MainPresenter()

        val recyclerView = fragment.findViewById<RecyclerView>(R.id.rv_countries)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecyclerAdapter(this, presenter)

        val pagesCount = ceil(presenter.listLength/ ITEM_ON_PAGE_COUNT.toDouble()).toInt()
        val ll_paging = fragment.findViewById<LinearLayout>(R.id.ll_paging)

        val b_previous = ll_paging.findViewById<ImageButton>(R.id.ib_left) //button "move to previous page"
        b_previous.setOnClickListener {
            val adapter: RecyclerAdapter = (recyclerView.adapter as RecyclerAdapter)
            adapter.setPage(adapter.offset-1)
        }

        val b_next = ll_paging.findViewById<ImageButton>(R.id.ib_right) //button "move to next page"
        b_next.setOnClickListener {
            val adapter: RecyclerAdapter = (recyclerView.adapter as RecyclerAdapter)
            adapter.setPage(adapter.offset+1)
        }

        val b_first = ll_paging.findViewById<TextView>(R.id.b_first) //button "move to first page"
        b_first.setOnClickListener {
            val adapter: RecyclerAdapter = (recyclerView.adapter as RecyclerAdapter)
            adapter.setPage(0)
        }

        val b_last = ll_paging.findViewById<TextView>(R.id.b_last) //button "move to last page"
        b_last.setOnClickListener {
            val adapter: RecyclerAdapter = (recyclerView.adapter as RecyclerAdapter)
            adapter.setPage(presenter.listLength / ITEM_ON_PAGE_COUNT)
        }
        if (pagesCount==1) //makes last page button invisible if there is only 1 page
            b_last.visibility = INVISIBLE
        else
            b_last.text = ((presenter.listLength / ITEM_ON_PAGE_COUNT) + 1).toString()

        return fragment
    }

    fun lineItemPressed(realPosition:Int){
        parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, ChartFragment.newInstance(realPosition))
                .addToBackStack("chart")
                .commit()
    }

    companion object{
        fun newInstance(): Fragment {
            return MainFragment()
        }
    }
}
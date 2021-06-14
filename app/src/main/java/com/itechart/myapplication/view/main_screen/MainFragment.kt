package com.itechart.myapplication.view.main_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itechart.myapplication.R
import com.itechart.myapplication.presenter.main.MainPresenter
import kotlin.math.ceil

class MainFragment: Fragment() {
    private lateinit var fragment:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_main, container, false)

        val recyclerView = fragment.findViewById<RecyclerView>(R.id.rv_list) //list of todo
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecyclerAdapter()

        val pagesCount = ceil(MainPresenter.listLength/ITEM_ON_PAGE_COUNT.toDouble()).toInt()
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
            adapter.setPage(MainPresenter.listLength / ITEM_ON_PAGE_COUNT)
        }
        if (pagesCount==1) //makes last page button invisible if there is only 1 page
            b_last.visibility = INVISIBLE
        else
            b_last.text = ((MainPresenter.listLength / ITEM_ON_PAGE_COUNT) + 1).toString()

        return fragment
    }

    companion object{
        fun newInstance(): Fragment {
            return MainFragment()
        }
    }
}
package com.itechart.covid_tracker.screens.main.view

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.app_level.dagger.module.storedActivity
import com.itechart.covid_tracker.model.network.CovidStatsProvider
import com.itechart.covid_tracker.screens.main.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.ceil

class MainFragment: Fragment() {
    private lateinit var fragment:View

    @Inject
    lateinit var viewModel: MainViewModel
    @Inject
    lateinit var covidStatsProvider: CovidStatsProvider

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_main, container, false)
        App.appComponent.inject(this)

        val recyclerView = fragment.findViewById<RecyclerView>(R.id.rv_countries)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecyclerAdapter(this, viewModel)

        initPaging(recyclerView)

        val et_search = fragment.findViewById<EditText>(R.id.et_search)
        et_search.addTextChangedListener {
            (recyclerView.adapter as RecyclerAdapter).filter(et_search.text.toString())
        }

        return fragment
    }

    //initialization of paging
    private fun initPaging(recyclerView: RecyclerView){
        val pagesCount = ceil(viewModel.countries.size/ ITEM_ON_PAGE_COUNT.toDouble()).toInt()
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
            adapter.setPage(viewModel.countries.size / ITEM_ON_PAGE_COUNT)
        }
        if (pagesCount==1) //makes last page button invisible if there is only 1 page
            b_last.visibility = INVISIBLE
        else
            b_last.text = ((viewModel.countries.size / ITEM_ON_PAGE_COUNT) + 1).toString()
    }

    fun updatePaging(){
        val pagesCount = ceil(viewModel.countries.size/ ITEM_ON_PAGE_COUNT.toDouble()).toInt()
        val ll_paging = fragment.findViewById<LinearLayout>(R.id.ll_paging)

        val b_last = ll_paging.findViewById<TextView>(R.id.b_last) //button "move to last page"
        if (pagesCount==1) //makes last page button invisible if there is only 1 page
            b_last.visibility = INVISIBLE
        else {
            b_last.text = ((viewModel.countries.size / ITEM_ON_PAGE_COUNT) + 1).toString()
            b_last.visibility = VISIBLE
        }
    }

    fun lineItemPressed(realPosition:Int){
        GlobalScope.launch {
            covidStatsProvider.preloadDays(realPosition)
            val navigation = App.appComponent.getNavigation()

            val args = Bundle().apply {
                putInt("position", realPosition)
            }
            val mainHandler = Handler(App.appComponent.getContext().mainLooper)
            mainHandler.post {
                navigation.navigate(R.id.action_mainFragment_to_chartFragment, args)
            }
        }
    }
}
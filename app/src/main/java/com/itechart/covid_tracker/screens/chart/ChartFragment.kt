package com.itechart.covid_tracker.screens.chart

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.app_level.dagger.App
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ChartFragment: Fragment() {
    private lateinit var fragment: View
    @Inject
    lateinit var viewModel: ChartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_chart, container, false)
        App.appComponent.inject(this)
        arguments?.let {
            viewModel.number = it.getInt("position")
        }
        viewModel.updateDays()

        //adding workManager for stats data updating
        val updateRequest = PeriodicWorkRequestBuilder<UpdateWorker>(1, TimeUnit.MINUTES)
                .build()
        WorkManager.getInstance(requireContext())
                    .enqueue(updateRequest)

        viewModel.days.observe(viewLifecycleOwner, {
            updateStats()
        })
        updateStats()

        return fragment
    }

    private fun updateStats(){
        val grid = fragment.findViewById<GridLayout>(R.id.grid)
        val iterator = grid.children.iterator()
        for (i in -10..-2){
            val view:TextView = iterator.next() as TextView
            if (viewModel.listLength+i>=0) { //if there are enough elements
                view.text = viewModel.days.value!![viewModel.listLength + i].shortText //then set text
                if ((viewModel.listLength + i-1 >=0) &&
                    (viewModel.days.value!![viewModel.listLength + i-1].count > viewModel.days.value!![viewModel.listLength + i].count))
                    view.setTextColor(Color.parseColor("#00FF00"))
                else
                    view.setTextColor(Color.parseColor("#FF0000"))
            }
            else
                view.visibility = INVISIBLE //else hide
        }

        val view:TextView = iterator.next() as TextView //last, long item
        if (viewModel.listLength>0) {
            view.text = viewModel.days.value!![viewModel.listLength - 1].text
            if ((viewModel.listLength >=2) &&
                (viewModel.days.value!![viewModel.listLength - 2].count > viewModel.days.value!![viewModel.listLength - 1].count))
                view.setTextColor(Color.parseColor("#00FF00"))
            else
                view.setTextColor(Color.parseColor("#FF0000"))
        }else
            view.visibility = INVISIBLE //else hide

        val chart = fragment.findViewById<LineChart>(R.id.chart)
        chart.description = Description().apply { text = "" }
        val legend = chart.legend
        legend.textColor = Color.WHITE

        val info = ArrayList<Entry>(viewModel.listLength) //info for chart
        for ((index, day) in viewModel.days.value!!.withIndex())
            info += Entry(index.toFloat(),day.count.toFloat())

        val lineDataSet = LineDataSet(info, "Statistics")
        lineDataSet.color = Color.RED
        lineDataSet.valueTextColor = Color.RED

        val lineData = LineData(lineDataSet)
        chart.data = lineData
        chart.invalidate()
    }
}
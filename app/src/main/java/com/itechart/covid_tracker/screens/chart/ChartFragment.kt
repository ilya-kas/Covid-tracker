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
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.itechart.covid_tracker.R

class ChartFragment: Fragment() {
    private lateinit var fragment:View
    private lateinit var viewModel: ChartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_chart, container, false)
        viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)
        arguments?.let {
            viewModel.number = it.getInt("nom")
        }

        val grid = fragment.findViewById<GridLayout>(R.id.grid)
        val iterator = grid.children.iterator()
        for (i in -10..-2){
            val view:TextView = iterator.next() as TextView
            if (viewModel.listLength+i>=0) { //if there are enough elements
                view.text = viewModel.days[viewModel.listLength + i].shortText //then set text
                if ((viewModel.listLength + i-1 >=0) &&
                    (viewModel.days[viewModel.listLength + i-1].count > viewModel.days[viewModel.listLength + i].count))
                    view.setTextColor(Color.parseColor("#00FF00"))
                else
                    view.setTextColor(Color.parseColor("#FF0000"))
            }
            else
                view.visibility = INVISIBLE //else hide
        }

        val view:TextView = iterator.next() as TextView //last, long item
        if (viewModel.listLength>0) {
            view.text = viewModel.days[viewModel.listLength - 1].text
            if ((viewModel.listLength >=2) &&
                    (viewModel.days[viewModel.listLength - 2].count > viewModel.days[viewModel.listLength - 1].count))
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
        for ((index, day) in viewModel.days.withIndex())
            info += Entry(index.toFloat(),day.count.toFloat())

        val lineDataSet = LineDataSet(info, "Statistics")
        lineDataSet.color = Color.RED
        lineDataSet.valueTextColor = Color.RED

        val lineData = LineData(lineDataSet)
        chart.data = lineData
        chart.invalidate()

        return fragment
    }

    companion object{
        fun newInstance(nom:Int): Fragment {
            val fragment = ChartFragment()
            fragment.arguments = Bundle().apply {
                    putInt("nom", nom)
            }
            return fragment
        }
    }
}
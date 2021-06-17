package com.itechart.covid_tracker.view.chart_screen

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
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.presenter.chart.ChartPresenter

class ChartFragment: Fragment() {
    private lateinit var fragment:View
    private lateinit var presenter:ChartPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragment = inflater.inflate(R.layout.fragment_chart, container, false)
        presenter = ChartPresenter()
        arguments?.let {
            presenter.number = it.getInt("nom")
        }

        val grid = fragment.findViewById<GridLayout>(R.id.grid)
        val iterator = grid.children.iterator()
        for (i in -10..-2){
            val view:TextView = iterator.next() as TextView
            if (presenter.listLength+i>=0) { //if there are enough elements
                view.text = presenter.days[presenter.listLength + i].shortText //then set text
                if (view.text.startsWith("-"))
                    view.setTextColor(Color.parseColor("#00FF00"))
                else
                    view.setTextColor(Color.parseColor("#FF0000"))
            }
            else
                view.visibility = INVISIBLE //else hide
        }

        val view:TextView = iterator.next() as TextView //last, long item
        if (presenter.listLength>0) {
            view.text = presenter.days[presenter.listLength - 1].text
            if (presenter.days[presenter.listLength - 1].count <= 0)
                view.setTextColor(Color.parseColor("#00FF00"))
            else
                view.setTextColor(Color.parseColor("#FF0000"))
        }else
            view.visibility = INVISIBLE //else hide

        val chart = fragment.findViewById<LineChart>(R.id.chart)
        chart.description = Description().apply { text = "" }
        val legend = chart.legend
        legend.textColor = Color.WHITE

        val info = ArrayList<Entry>(presenter.listLength) //info for chart
        for ((index, day) in presenter.days.withIndex())
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
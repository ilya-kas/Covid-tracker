package com.itechart.myapplication.presenter.chart

import com.itechart.myapplication.model.Model

object ChartPresenter {
    var number = 0 //which group we are looking at
        set(value) {
            field = value
            list = Model.loadItems(value)
        }
    lateinit var list: ArrayList<Item>//todo rename
    val listLength
        get() = list.size
}
package com.itechart.covid_tracker.model.entities

import java.time.MonthDay

class Day(private val date:MonthDay, val count:Int) {
    val text
        get() = date.dayOfMonth.toString() + " " + date.month + ": " + count
    val shortText
        get() = count.toString()
}
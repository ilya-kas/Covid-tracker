package com.itechart.myapplication.presenter.main

import androidx.fragment.app.FragmentManager
import com.itechart.myapplication.model.Model

object MainPresenter {
    val list = Model.loadGroups() //todo rename
    val listLength
        get() = list.size
    lateinit var fragmentManager: FragmentManager

    fun starred(nom: Int){
        list[nom].favorite = !list[nom].favorite
    }
}
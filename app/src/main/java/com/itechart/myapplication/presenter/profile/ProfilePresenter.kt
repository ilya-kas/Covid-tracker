package com.itechart.myapplication.presenter.profile

import com.itechart.myapplication.model.Model

object ProfilePresenter {
    var user = Model.loadUser()

    fun updateUserInfo(){
        user = Model.loadUser()
    }
}
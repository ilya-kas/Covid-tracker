package com.itechart.covid_tracker.model.accounts

const val OK = 0
const val WRONG_PASSWORD = 1
const val LOGIN_IS_TAKEN = 2

interface AccountsManager {
    suspend fun logIn(login: String, encPassword: String): Int
    suspend fun register(login: String, encPassword: String): Int
}
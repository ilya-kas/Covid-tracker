package com.itechart.covid_tracker.model.accounts

import android.util.Log
import com.google.firebase.database.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseManager @Inject constructor(): AccountsManager {
    val database = FirebaseDatabase.getInstance("https://covid-tracker-320714-default-rtdb.europe-west1.firebasedatabase.app")

    val data = mutableMapOf<String, String>()

    init {
        val reference = database.getReference("users")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                data.clear()
                for (child in snapshot.children)
                    data[child.key!!] = child.value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        reference.child("any").setValue("null")
    }

    override suspend fun logIn(login: String, encPassword: String): Int {
        return if (data[login] == encPassword)
            OK
        else
            WRONG_PASSWORD
    }

    private fun isLoginFree(login: String): Boolean = data[login] != null

    override suspend fun register(login: String, encPassword: String): Int {
        if (!isLoginFree(login))
            return LOGIN_IS_TAKEN
        val reference = database.getReference("users")
        reference.child(login).setValue(encPassword)
        return OK
    }
}
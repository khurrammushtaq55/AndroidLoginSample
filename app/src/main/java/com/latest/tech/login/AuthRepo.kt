package com.latest.tech.login

import android.content.Context
import android.widget.Toast

class AuthRepo(private val context: Context) {

    fun login(username: String, password: String) {

        Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()

    }
}
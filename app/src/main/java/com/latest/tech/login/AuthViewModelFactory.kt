package com.latest.tech.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(private val context: AuthActivity) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) return AuthViewModel(AuthRepo(context)) as T
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}
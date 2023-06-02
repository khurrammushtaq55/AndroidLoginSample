package com.latest.tech.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.latest.tech.R

class AuthViewModel(private val authRepo: AuthRepo) : ViewModel() {


    val authFormState = MutableLiveData<AuthFormState>()
    fun authenticate(userName: String, password: String) {
        val result= authRepo.login(userName,password)
    }

    fun dataChange(userName: String?, password: String?) {
        if (!isUserValid(userName)) {
            authFormState.value = AuthFormState(userNameError = R.string.invalid_username)
        } else
            if (!isPasswordValid(password)) {
                authFormState.value = AuthFormState(passwordError = R.string.invalid_password)
            } else authFormState.value = AuthFormState(isDataValid = true)
    }

    private fun isUserValid(username: String?): Boolean {
        if(username==null) return true
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String?): Boolean {
        if(password==null)return true
        return password.length > 5
    }

}
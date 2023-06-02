package com.latest.tech.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.latest.tech.databinding.ActivityAuthBinding
import com.latest.tech.ui.login.afterTextChanged

class AuthActivity : AppCompatActivity() {


    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading

        val authViewModel= ViewModelProvider(this,AuthViewModelFactory())[AuthViewModel::class.java]


        //error handling
        authViewModel.authFormState.observe(this){

            login.isEnabled=it.isDataValid
            userName.error= it.userNameError?.let { it1 -> getString(it1) }
            password.error= it.passwordError?.let { it1 -> getString(it1) }
        }


        //text change listener
        userName.afterTextChanged {
            authViewModel.dataChange(userName.text.toString(),null)
        }
        password.afterTextChanged {
            authViewModel.dataChange(null,password.text.toString())
        }

        //click listener
        login.setOnClickListener {

            loading.visibility=View.VISIBLE
            authViewModel.authenticate(userName = userName.text.toString(), password = password.text.toString())
        }

    }
}
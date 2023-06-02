package com.latest.tech.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.latest.tech.databinding.ActivitySplashBinding
import com.latest.tech.login.AuthActivity

class SplashActivity: AppCompatActivity() {

    private lateinit var binding:ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spButton= binding.spButton
        spButton.text="Click Me"
        spButton.setOnClickListener {
            (it as Button).text = "Clicked"
            Thread.sleep(500)
            val intent=Intent(this@SplashActivity,AuthActivity::class.java )
            startActivity(intent)
        }
    }

}
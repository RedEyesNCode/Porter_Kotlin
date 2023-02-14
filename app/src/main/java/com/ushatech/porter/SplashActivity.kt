package com.ushatech.porter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ushatech.porter.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySplashBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        initClicks()
        Handler().postDelayed(Runnable {
            startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
        },1500)


        setContentView(binding.root)
    }

    private fun initClicks() {


    }
}
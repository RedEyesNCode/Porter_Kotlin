package com.ushatech.porter.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ushatech.porter.databinding.ActivitySplashBinding
import com.ushatech.porter.utils.SessionKeys
import com.ushatech.porter.utils.SimpleSession

class SplashActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySplashBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        initClicks()
        Handler().postDelayed(Runnable {
            try {
                if(SimpleSession(this@SplashActivity).getBoolean(SessionKeys.IS_INTRO_DONE)){
                    startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }
            }catch (e:java.lang.Exception){
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }



        },1500)


        setContentView(binding.root)
    }

    private fun initClicks() {


    }
}
package com.ushatech.porter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ushatech.porter.databinding.ActivityUserDetailsBinding

class UserDetailsActivity : BaseActivity() {

    private lateinit var binding:ActivityUserDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicks()

    }

    private fun initClicks() {
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this@UserDetailsActivity,DashboardActivity::class.java))

        }
    }
}
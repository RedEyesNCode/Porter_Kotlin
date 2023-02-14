package com.ushatech.porter.ui.activity

import android.content.Intent
import android.os.Bundle
import com.ushatech.porter.databinding.ActivityUserDetailsBinding
import com.ushatech.porter.presentation.BaseActivity

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
            startActivity(Intent(this@UserDetailsActivity, DashboardActivity::class.java))

        }
    }
}
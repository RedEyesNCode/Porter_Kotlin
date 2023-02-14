package com.ushatech.porter.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUiSaveStateControl
import com.ushatech.porter.R
import com.ushatech.porter.databinding.ActivityDashboardBinding
import com.ushatech.porter.presentation.BaseActivity

class DashboardActivity : BaseActivity() {

    private lateinit var binding : ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        initNav()
        setContentView(binding.root)
    }

    @OptIn(NavigationUiSaveStateControl::class)
    private fun initNav() {
        val navController = Navigation.findNavController(this@DashboardActivity, R.id.activity_main_nav_host_fragment)
        NavigationUI.setupWithNavController(binding.bottomNavigationbar, navController, false)
    }
}
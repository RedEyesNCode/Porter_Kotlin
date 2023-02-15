package com.ushatech.porter.ui.activity

import android.content.Intent
import android.os.Bundle
import com.ushatech.porter.databinding.ActivityUserDetailsBinding
import com.ushatech.porter.presentation.BaseActivity
import com.ushatech.porter.utils.AppUtils
import com.ushatech.porter.utils.SessionKeys
import com.ushatech.porter.utils.SimpleSession

class UserDetailsActivity : BaseActivity() {

    private lateinit var binding:ActivityUserDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicks()
        getUserNumber()

    }

    private fun getUserNumber() {
        val number = intent.getStringExtra("USER_NUMBER")
        binding.tvUserNumber.setText("$number")
    }

    private fun initClicks() {
        binding.btnRegister.setOnClickListener {
            if(isValidated()){

                val dashboardIntent =Intent(this@UserDetailsActivity, DashboardActivity::class.java)
                dashboardIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                SimpleSession(this@UserDetailsActivity).put(SessionKeys.IS_INTRO_DONE,true)
                startActivity(dashboardIntent)

            }

        }
        binding.tvChangeNumber.setOnClickListener {
            finish()

        }
    }

    private fun isValidated():Boolean{
        if(binding.edtFirstName.text.toString().isEmpty()){
            binding.edtFirstName.error = "Please enter first name"

            return false
        }else if(binding.edtLastName.text.toString().isEmpty()){

            binding.edtLastName.error = "Please enter last name."
            return false

        }else if(binding.edtEmailId.text.toString().isEmpty()){
            binding.edtEmailId.error = "Please enter email id."
            return false

        }else if(AppUtils().validate(binding.edtEmailId.text.toString())){
            binding.edtEmailId.error = "Please enter valid email id."

            return false
        }else{

            return true
        }




    }
}
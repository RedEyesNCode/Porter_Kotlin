package com.ushatech.porter.ui.activity

import android.content.Intent
import android.os.Bundle
import com.ushatech.porter.databinding.ActivityLoginBinding
import com.ushatech.porter.presentation.BaseActivity

class LoginActivity : BaseActivity() {

    private lateinit var binding:ActivityLoginBinding

    var countryCode:String = "+91"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        initClicks()
        setContentView(binding.root)
    }

    private fun initClicks() {
        binding.btnLogin.setOnClickListener {
            if(isValidated()){
                startActivity(Intent(this@LoginActivity, VerifyOtpActivity::class.java))
                finish()
            }


        }
    }
    private fun isValidated():Boolean{
        if(binding.edtMobileNumber.text.toString().isEmpty()){
            showToast("Please enter mobile number.")
            return false
        }else if(binding.edtMobileNumber.text.toString().length<10){
            showToast("Please enter valid mobile number,")
            return false
        }else{

            return true
        }



    }
}
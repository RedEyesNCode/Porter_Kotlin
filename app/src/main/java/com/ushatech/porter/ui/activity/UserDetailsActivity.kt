package com.ushatech.porter.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ushatech.porter.databinding.ActivityUserDetailsBinding
import com.ushatech.porter.presentation.BaseActivity
import com.ushatech.porter.presentation.SignupViewModel
import com.ushatech.porter.utils.AppUtils
import com.ushatech.porter.utils.SessionKeys
import com.ushatech.porter.utils.SimpleSession

class UserDetailsActivity : BaseActivity() {

    private lateinit var binding:ActivityUserDetailsBinding
    private lateinit var viewModel:SignupViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicks()
        setupViewModel()
        attachObservers()
        getUserNumber()

    }

    private fun attachObservers() {

        viewModel.isFailed.observe((this)){
            hideLoader()
            if(it!=null){
                showToast(it)
            }
        }
        viewModel.isSuccess.observe((this)){
            if(it){
                showLoader()
            }else{
                hideLoader()
            }
        }
        viewModel.registerUserResponse.observe((this)){
            hideLoader()
            if(it!=null){
               if(it.status?.toInt()==0){
                   // Move to dashboard
                   SimpleSession(this@UserDetailsActivity).put(SessionKeys.IS_INTRO_DONE,true)
                   val dashboardIntent = Intent(this@UserDetailsActivity,DashboardActivity::class.java)
                   dashboardIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                   startActivity(dashboardIntent)
               }else{
                   showToast(it.message.toString())
               }
            }
        }
    }

    private fun setupViewModel() {
        viewModel = SignupViewModel()
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        binding.viewmodel = viewModel

    }

    private fun getUserNumber() {
        val number = intent.getStringExtra("USER_NUMBER")
        if(number!!.isEmpty()){

            binding.userNumberLayout.visibility = View.GONE
            binding.edtNumberLayout.visibility = View.VISIBLE
        }else{
            binding.userNumberLayout.visibility = View.GONE
            binding.edtNumberLayout.visibility = View.GONE
        }


        binding.tvUserNumber.setText("$number")
    }

    private fun initClicks() {
        binding.btnRegister.setOnClickListener {
            if(isValidated()){

                showLoader()
                viewModel.signupUser(binding.edtFirstName.text.toString(),binding.edtLastName.text.toString(),binding.edtEmailId.text.toString(),binding.edtMobileNumber.text.toString())




            }

        }
        binding.tvChangeNumber.setOnClickListener {
            finish()

        }
    }

    private fun isValidated():Boolean{
        if(binding.edtNumberLayout.visibility==View.VISIBLE){
            if(binding.edtFirstName.text.toString().isEmpty()){
                binding.edtFirstName.error = "Please enter first name"

                return false
            }else if(binding.edtLastName.text.toString().isEmpty()){

                binding.edtLastName.error = "Please enter last name."
                return false

            }else if(binding.edtEmailId.text.toString().isEmpty()){
                binding.edtEmailId.error = "Please enter email id."
                return false
            }else if(!AppUtils().validate(binding.edtEmailId.text.toString())){
                binding.edtEmailId.error = "Please enter valid email id."
                return false
            }else if(binding.edtMobileNumber.text.toString().isEmpty()){

                binding.edtMobileNumber.error = "Plesa enter mobile number"
                return false
            }else{

                return true
            }
        }else{
            if(binding.edtFirstName.text.toString().isEmpty()){
                binding.edtFirstName.error = "Please enter first name"

                return false
            }else if(binding.edtLastName.text.toString().isEmpty()){

                binding.edtLastName.error = "Please enter last name."
                return false

            }else if(binding.edtEmailId.text.toString().isEmpty()){
                binding.edtEmailId.error = "Please enter email id."
                return false
            }else if(!AppUtils().validate(binding.edtEmailId.text.toString())){
                binding.edtEmailId.error = "Please enter valid email id."
                return false
            }else{

                return true
            }
        }






    }
}
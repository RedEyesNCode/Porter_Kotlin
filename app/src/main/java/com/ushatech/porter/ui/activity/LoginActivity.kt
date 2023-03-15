package com.ushatech.porter.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ushatech.porter.R
import com.ushatech.porter.databinding.ActivityLoginBinding
import com.ushatech.porter.databinding.BottomSheetOtpVerifyBinding
import com.ushatech.porter.presentation.BaseActivity
import com.ushatech.porter.presentation.LoginViewModel
import com.ushatech.porter.utils.SessionKeys
import com.ushatech.porter.utils.SimpleSession

class LoginActivity : BaseActivity() {

    private lateinit var binding:ActivityLoginBinding

    var countryCode:String = "+91"

    private lateinit var viewModel:LoginViewModel
    private lateinit var bottomSheetDialog: BottomSheetDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        initClicks()
        setupViewModel()
        attachObservers()
        setContentView(binding.root)
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
        viewModel.commonResponse.observe((this)){
            hideLoader()
            if(it.status?.toInt()==1){
                //success

                showToast(it.message.toString())
                showBottomOtpSheet()
            }else{
                showToast("User does not exists ! Please try again.")
            }

        }
        viewModel.loginUserResponse.observe((this)){
            hideLoader()
            if(it.status?.toInt()==0){
                // success
                // Storing token and other user data into the session after login sucess.
                SimpleSession(this@LoginActivity).put(SessionKeys.IS_INTRO_DONE,true)
                SimpleSession(this@LoginActivity).putObject(SessionKeys.USER,it)
                val dashboardIntent = Intent(this@LoginActivity,DashboardActivity::class.java)
                dashboardIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(dashboardIntent)
            }else{
                showToast("Login User failed")

            }






        }


    }

    private fun showBottomOtpSheet() {
        val bottomSheetVerifyOtpBinding = BottomSheetOtpVerifyBinding.inflate(LayoutInflater.from(this@LoginActivity))
        bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        bottomSheetDialog.setContentView(bottomSheetVerifyOtpBinding.root)
        bottomSheetDialog.setCancelable(true)
        bottomSheetDialog.show()
        bottomSheetVerifyOtpBinding.btnVerifyOtp.setOnClickListener {
            if(bottomSheetVerifyOtpBinding.otpView.otp.toString().isEmpty()){

                showToast("Please enter valid otp")
            }else{
                showLoader()
                viewModel.loginUser(binding.edtMobileNumber.text.toString(),bottomSheetVerifyOtpBinding.otpView.otp.toString())


            }


        }



    }

    private fun setupViewModel() {
        viewModel = LoginViewModel()
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewmodel = viewModel


    }

    private fun initClicks() {
        binding.btnLogin.setOnClickListener {
            if(isValidated()){
                showLoader()
                viewModel.checkUserLogin(binding.edtMobileNumber.text.toString())

                // Not Finishing the activity until next login. to dashboard
            }
        }
        binding.btnSingup.setOnClickListener {
            val userDetailsIntent = Intent(this@LoginActivity,UserDetailsActivity::class.java)
            userDetailsIntent.putExtra("USER_NUMBER","")
            startActivity(userDetailsIntent)


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
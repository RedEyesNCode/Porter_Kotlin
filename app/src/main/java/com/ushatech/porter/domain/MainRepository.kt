package com.ushatech.porter.domain

class MainRepository {

    suspend fun signupUser(firstName:String,lastName:String,email:String,contactNo:String) = AndroidClient().apiInterface.signupUser(firstName,lastName,contactNo,email)

    suspend fun checkUserLogin(mobileNumber:String)  = AndroidClient().apiInterface.checkLogin(mobileNumber)

    suspend fun loginUser(mobileNumber: String,otp:String) = AndroidClient().apiInterface.loginUser(mobileNumber, otp)

}
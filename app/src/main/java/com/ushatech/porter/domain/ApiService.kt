package com.ushatech.porter.domain

import com.google.android.gms.common.internal.service.Common
import com.ushatech.porter.data.CommonResponse
import com.ushatech.porter.data.LoginUserResponse
import com.ushatech.porter.data.RegistrationResponse
import com.ushatech.porter.data.RequirementMetaResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {


    // status 0 is success
    @FormUrlEncoded
    @POST("registration.php")
    fun signupUser(@Field("fname") firstName:String,@Field("lname") lastName:String,@Field("contact_no") number:String,@Field("email") email:String):Call<RegistrationResponse>



    // Status 2-- user does not exits
    // status 1 --> success.
    @FormUrlEncoded
    @POST("check_login.php")
    fun checkLogin(@Field("mobile") mobileNumber:String):Call<CommonResponse>


    //status 0--> success, status-->1 fail login
    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(@Field("mobile") mobileNumber: String,@Field("otp") otp:String):Call<LoginUserResponse>

    @POST("requirements.php")
    fun getRequirementMeta():Call<RequirementMetaResponse>


}
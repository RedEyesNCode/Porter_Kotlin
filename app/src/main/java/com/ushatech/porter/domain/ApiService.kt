package com.ushatech.porter.domain

import com.ushatech.porter.data.CommonResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("sign.php")
    fun signupUser(@Body map:HashMap<String,String>):Call<CommonResponse>

}
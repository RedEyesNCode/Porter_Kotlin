package com.ushatech.porter.domain

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AndroidClient {


//    val AE_STORES_LIVE_URL = "https://appawheels.com/" // When running in the local host.
    val PORTER_CLONE_URL = "https://ushatechsolution.com/ApnawheelsAPI/" // When running in the local host.


    val retrofitClient: Retrofit.Builder by lazy {
        val levelType = HttpLoggingInterceptor.Level.BODY
        val debugInterceptor= HttpLoggingInterceptor().setLevel(levelType)
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(debugInterceptor)

        Retrofit.Builder().baseUrl(PORTER_CLONE_URL).client(okHttpClient.build()).addConverterFactory(
            GsonConverterFactory.create())
    }

    val apiInterface:ApiService by lazy {
        retrofitClient.build().create(ApiService::class.java)
    }

}
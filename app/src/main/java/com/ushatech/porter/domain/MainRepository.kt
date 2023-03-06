package com.ushatech.porter.domain

class MainRepository {

    suspend fun signupUser(map:HashMap<String,String>) = AndroidClient().apiInterface.signupUser(map)

}
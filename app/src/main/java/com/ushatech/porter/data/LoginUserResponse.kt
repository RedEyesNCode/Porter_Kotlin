package com.ushatech.porter.data

import com.google.gson.annotations.SerializedName

data class LoginUserResponse( @SerializedName("status"  ) var status  : Int?    = null,
                              @SerializedName("message" ) var message : String? = null,
                              @SerializedName("user_id" ) var userId  : String? = null,
                              @SerializedName("token"   ) var token   : String? = null
)

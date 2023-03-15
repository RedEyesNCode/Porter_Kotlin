package com.ushatech.porter.data

import com.google.gson.annotations.SerializedName

data class RegistrationResponse( @SerializedName("status"  ) var status  : Int?    = null,
                                 @SerializedName("message" ) var message : String? = null,
                                 @SerializedName("data"    ) var data    : Data?   = Data()){

    data class Data (

        @SerializedName("fname"      ) var fname     : String? = null,
        @SerializedName("lname"      ) var lname     : String? = null,
        @SerializedName("contact_no" ) var contactNo : String? = null,
        @SerializedName("email"      ) var email     : String? = null,
        @SerializedName("user_id"    ) var userId    : String?    = null,
        @SerializedName("token"      ) var token     : String? = null

    )
}

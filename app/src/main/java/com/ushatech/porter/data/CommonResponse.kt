package com.ushatech.porter.data

import com.google.gson.annotations.SerializedName

data class CommonResponse(@SerializedName("status"  ) var status  : String? = null,
                          @SerializedName("message" ) var message : String? = null)

package com.ushatech.porter.data

import com.google.gson.annotations.SerializedName

class RequirementMetaResponse(
    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()) {
    data class Data (

        @SerializedName("requirement" ) var requirement : String? = null

    )
}
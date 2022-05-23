package com.oscar.movilidad.model

import com.google.gson.annotations.SerializedName

data class AccessToken(

    @SerializedName("auth_token")
    var authToken: String? = null

)

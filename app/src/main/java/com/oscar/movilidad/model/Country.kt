package com.oscar.movilidad.model

import com.google.gson.annotations.SerializedName

data class Country(

    @SerializedName("country_name")
    val countryName: String,

    @SerializedName("country_short_name")
    val countryShortName: String,

    @SerializedName("country_phone_code")
    val countryPhoneCode: Int

)

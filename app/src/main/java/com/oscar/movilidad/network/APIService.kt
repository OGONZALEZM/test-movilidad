package com.oscar.movilidad.network

import com.oscar.movilidad.model.AccessToken
import com.oscar.movilidad.model.Country
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface APIService {

    @GET("getaccesstoken")
    @Headers(
        "Accept: application/json",
        "api-token: jISZPkjZVtuavk6AQbBl-svDXkDNz0nNepjM0QlkC7MZ4tgGaM7Moob2cSQci1Btm3Q",
        "user-email: oscarmiguel182@gmail.com"
    )
    suspend fun getAuthToken(): AccessToken

    @GET("countries")
    suspend fun getCountries(): List<Country>

}
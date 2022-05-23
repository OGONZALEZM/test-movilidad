package com.oscar.movilidad.repository

import com.oscar.movilidad.network.APIService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val service: APIService
) {

    suspend fun getAuthToken() = service.getAuthToken()

    suspend fun getCountries() = service.getCountries()

}
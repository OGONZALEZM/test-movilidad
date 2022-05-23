package com.oscar.movilidad.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.oscar.movilidad.model.Country
import com.oscar.movilidad.network.Resource
import com.oscar.movilidad.network.TokenManager
import com.oscar.movilidad.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private var _countriesLiveData: MutableLiveData<List<Country>> = MutableLiveData()
    val countriesLiveData: LiveData<List<Country>>
        get() = _countriesLiveData
    
    fun getCountries() = liveData {
        emit(Resource.loading(null))
        try {
            val countries = mainRepository.getCountries()
            _countriesLiveData.postValue(countries)
            emit(Resource.success(true))
        }  catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val code = throwable.code()
                    if (code == 500) {
                        emit(Resource.error("Buscando token...", false))
                    } else {
                        emit(Resource.error("Error de conexión con el servidor", true))
                    }
                }
                else -> {
                    emit(Resource.error("Error en la app", null))
                }
            }
        }
    }

    fun getAccessToken() = liveData {
        try {
            emit(Resource.loading(null))
            val token = mainRepository.getAuthToken()
            tokenManager.saveToken(token)
            emit(Resource.success(true))
        } catch (exception: Exception) {
            emit(Resource.error("Error obteniendo token.", null))
        }
    }

}
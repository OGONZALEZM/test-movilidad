package com.oscar.movilidad.network

import android.content.Context
import android.content.SharedPreferences
import com.oscar.movilidad.model.AccessToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(
    @ApplicationContext val context: Context
) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    companion object {
        const val PREFERENCES_NAME = "test_movilidad"
        const val AUTH_TOKEN = "auth_token"
    }

    fun saveToken(token: AccessToken) {
        prefs.edit().putString(AUTH_TOKEN, token.authToken).apply()
    }

    fun getToken(): AccessToken {
        val token = AccessToken()
        token.authToken = prefs.getString(AUTH_TOKEN, "").toString()
        return token
    }

    fun delete() {
        prefs.edit().remove(AUTH_TOKEN).apply()
    }

}
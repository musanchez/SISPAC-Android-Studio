package com.example.gestion_sispac.ui.theme.repository

import android.util.Log
import com.example.gestion_sispac.ui.theme.remote.ApiAdapter
import com.example.gestion_sispac.ui.theme.remote.ApiUser
import com.example.gestion_sispac.ui.theme.response.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import retrofit2.Response



class RepositoryUser : CoroutineScope by MainScope() {

    val apiUser : ApiUser = ApiAdapter.getInstance()
        .create(ApiUser::class.java)
    suspend fun fetchData(cif: String, password: String): Result<LoginResponse> {
        var  loginResponse : LoginResponse = LoginResponse()
        return try {
            val response: Response<LoginResponse> = apiUser.getLogin(cif, password)
            loginResponse = response.body() as LoginResponse
            Log.d("RESULTADO OK", "RESULTADO OK,$loginResponse.msg")
            Result.success(loginResponse)

        } catch (e: Exception) {
            Log.d("ERROR", "$e.message")
            Result.failure(e)
        }
    }
}
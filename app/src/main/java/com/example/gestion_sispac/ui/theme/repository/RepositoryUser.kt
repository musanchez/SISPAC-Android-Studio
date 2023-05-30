package com.example.gestion_sispac.ui.theme.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.gestion_sispac.ui.theme.remote.ApiAdapter
import com.example.gestion_sispac.ui.theme.remote.ApiUser
import com.example.gestion_sispac.ui.theme.response.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Response



class RepositoryUser : CoroutineScope by MainScope() {

    val apiUser : ApiUser = ApiAdapter.getInstance()
        .create(ApiUser::class.java)
    suspend fun fetchData(name: String, password: String): Result<LoginResponse> {
        var  loginResponse : LoginResponse = LoginResponse()
        return try {
            val response: Response<LoginResponse> = apiUser.getLogin(name, password)
            loginResponse = response.body() as LoginResponse
            Log.d("RESULTADO OK", "RESULTADO OK,$loginResponse.msg")
            Result.success(loginResponse)

        } catch (e: Exception) {
            Log.d("ERROR", "$e.message")
            Result.failure(e)
        }
    }
}
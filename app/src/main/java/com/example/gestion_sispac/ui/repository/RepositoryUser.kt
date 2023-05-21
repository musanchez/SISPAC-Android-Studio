package com.example.gestion_sispac.ui.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.gestion_sispac.ui.remote.ApiAdapter
import com.example.gestion_sispac.ui.remote.ApiUser
import com.example.gestion_sispac.ui.response.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Response

class RepositoryUser : CoroutineScope by MainScope() {
    val apiUser : ApiUser = ApiAdapter.getInstance().create(ApiUser::class.java)

    fun fetchData(context : Context, name: String, password: String): LoginResponse {
        var  loginResponse : LoginResponse = LoginResponse()
        launch {
            try {
                val response: Response<LoginResponse> = apiUser.getLogin(name, password)
                if (response.isSuccessful) {
                    loginResponse = response.body() as LoginResponse
                    if (loginResponse.success) {
                        Toast.makeText(context, "Bienvenido: $name", Toast.LENGTH_LONG).show()
                        Log.d("RESULTADO OK", "RESULTADO OK,$loginResponse.msg")
                    }
                    else {
                        loginResponse.msg = "Sin exito"
                        loginResponse.success=false
                        Toast.makeText(context,"Usuario no existe verifique",Toast.LENGTH_LONG).show()
                    }
                } else {
                    loginResponse.msg = "Sin exito"
                    loginResponse.success=false
                    Toast.makeText(context,"ERROR AL BUSCAR AL USUARIO,${response.errorBody().toString()}",Toast.LENGTH_LONG).show()
                    Log.d("RESULTADO FALLO","RESULTADO FALLO,$loginResponse.msg")
                }
            } catch (e: Exception) {
                Log.d("ERROR", "$e.message")
            }
        }
        return loginResponse;
    }
}
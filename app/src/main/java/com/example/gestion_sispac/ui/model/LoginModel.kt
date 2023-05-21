package com.example.gestion_sispac.ui.model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gestion_sispac.ui.repository.RepositoryUser
import com.example.gestion_sispac.ui.response.LoginResponse

class LoginModel: ViewModel() {
    var name by mutableStateOf("")
    var password by mutableStateOf("")

    fun onSubmit(context: Context): LoginResponse {
        Log.d("Valores", "Valor name:$name, valor password: $password")
        val repository = RepositoryUser()
        return repository.fetchData(context, name, password)

    }


}
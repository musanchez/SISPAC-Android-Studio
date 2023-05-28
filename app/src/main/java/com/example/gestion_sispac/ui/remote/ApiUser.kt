package com.example.gestion_sispac.ui.remote

import com.example.gestion_sispac.ui.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


@SuppressWarnings("unchecked")
interface ApiUser {
    @GET("/api/systemuser/login")
    suspend fun getLogin(@Query("cif") cif: String, @Query("password") password :String): Response<LoginResponse>

}
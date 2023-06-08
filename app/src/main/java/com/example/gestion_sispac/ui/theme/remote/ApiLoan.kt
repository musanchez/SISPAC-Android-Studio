package com.example.gestion_sispac.ui.theme.remote

import com.example.gestion_sispac.ui.theme.dto.LoanDto
import com.example.gestion_sispac.ui.theme.model.Loan
import com.example.gestion_sispac.ui.theme.model.LoanItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiLoan {
    @GET("api/loan/byuser/{cif}")
    suspend fun getByUser(@Path("cif") cif: String): Loan

    @POST("api/loan/save")
    suspend fun loanCreate(@Body loan: LoanDto)
}
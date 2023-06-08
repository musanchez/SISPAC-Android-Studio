package com.example.gestion_sispac.ui.theme.repository

import android.util.Log
import com.example.gestion_sispac.ui.theme.model.Loan
import com.example.gestion_sispac.ui.theme.model.LoanItem
import com.example.gestion_sispac.ui.theme.remote.ApiAdapter
import com.example.gestion_sispac.ui.theme.remote.ApiLoan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class RepositoryLoan {

    private val apiLoan : ApiLoan = ApiAdapter.getInstance().create(ApiLoan::class.java)

    suspend fun getByUser(user : String) : List<LoanItem> {

        try {
            val loans = apiLoan.getByUser(user)
            Log.d("MENSAJE", "$loans")
            return loans
        } catch (e: Exception) {
            Log.d("Exception", e.message.toString())
        }
        return emptyList<LoanItem>()

    }
}
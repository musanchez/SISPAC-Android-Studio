package com.example.gestion_sispac.ui.theme.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestion_sispac.ui.theme.model.Loan
import com.example.gestion_sispac.ui.theme.model.LoanItem
import com.example.gestion_sispac.ui.theme.repository.RepositoryLoan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoanViewModel : ViewModel() {

    data class UIState(
        val _loading: Boolean = false,
        val loans : List<LoanItem> = emptyList<LoanItem>()
    )
    private val _state = MutableStateFlow<UIState>(UIState())

    val _loanState: StateFlow<UIState> = _state


    private val repositoryLoan = RepositoryLoan()

    fun loansUser(cif: String) {
        viewModelScope.launch {
            _state.update {it.copy(_loading = true)}
            val response =  repositoryLoan.getByUser(cif)
            Log.d("RESPONSE", "$response")
            _state.update { it.copy(loans = response) }
            _state.update {it.copy(_loading = false)}
        }
    }

}
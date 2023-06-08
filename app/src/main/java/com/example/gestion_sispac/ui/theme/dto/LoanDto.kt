package com.example.gestion_sispac.ui.theme.dto

import android.service.autofill.FieldClassification

data class LoanDto(
    val dateIssued : String,
    val returnDate : String,
    val username : String,
    val bookISBNlist : List<String>
)

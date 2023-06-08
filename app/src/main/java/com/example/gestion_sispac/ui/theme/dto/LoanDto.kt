package com.example.gestion_sispac.ui.theme.dto


data class LoanDto(
    val dateIssued : String,
    val returnDate : String,
    val username : String,
    val bookISBNlist : List<String>
)

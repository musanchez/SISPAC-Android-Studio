package com.example.gestion_sispac.ui.theme.model

data class LoanItem(
    val dateIssued: String,
    val dateCreated: String,
    val returnDate: String,
    val id: String,
    val loanStatus: String,
    val copies: List<Copy>,
    val systemUser: SystemUserItem
)
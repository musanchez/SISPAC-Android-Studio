package com.example.gestion_sispac.ui.theme.model

data class Book(
    val isbn : String,
    val title : String,
    val publisher : String,
    val classification: String,
    val authors : List<String>
    //val status: Boolean
)

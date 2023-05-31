package com.example.gestion_sispac.ui.theme.model

data class GenBookItem(
    val authors: List<Author>,
    val classification: Classification,
    val existence: Int,
    val isbn: String,
    val mfn: String,
    val publisher: Publisher,
    val title: String
)
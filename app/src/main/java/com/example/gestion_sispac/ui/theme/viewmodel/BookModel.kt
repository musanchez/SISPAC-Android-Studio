package com.example.gestion_sispac.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestion_sispac.ui.theme.model.GenBookItem
import com.example.gestion_sispac.ui.theme.repository.RepositoryBook
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookModel : ViewModel() {
    data class UIState(
        val _loading: Boolean = false,
        val listLibro: List<GenBookItem?> = emptyList<GenBookItem>()
    )

    val bookRepo : RepositoryBook = RepositoryBook()

    private val _bookState = MutableStateFlow<UIState>(UIState())
    val bookoState: StateFlow<UIState> = _bookState

    init {
        viewModelScope.launch {
            _bookState.update {it.copy(_loading = true)}
            val response =  bookRepo.getAll()
            _bookState.update { it.copy(listLibro = response) }
            _bookState.update {it.copy(_loading = false)}
        }
    }

    //dont know if its good
    suspend fun addOneByTitle(title: String) {
        viewModelScope.launch {
            _bookState.update {it.copy(_loading = true)}
            val response :  ArrayList<GenBookItem?> = ArrayList<GenBookItem?>()
            response.add(bookRepo.getByTitle(title))
            val test : List<GenBookItem?> = response
            _bookState.update { it.copy(listLibro = test) }
            _bookState.update {it.copy(_loading = false)}
        }
    }

    //dont know if its good
    suspend fun addManyByAuthor(authorName: String) {
        viewModelScope.launch {
            _bookState.update {it.copy(_loading = true)}
            val response =  bookRepo.getByAuthor(authorName)
            _bookState.update { it.copy(listLibro = response) }
            _bookState.update {it.copy(_loading = false)}
        }
    }

    //dont know if its good
    suspend fun addManyByPublisher(publisherName: String) {
        viewModelScope.launch {
            _bookState.update {it.copy(_loading = true)}
            val response =  bookRepo.getByPublisher(publisherName)
            _bookState.update { it.copy(listLibro = response) }
            _bookState.update {it.copy(_loading = false)}
        }
    }

    //dont know if its good
    suspend fun addManyByClassification(classificationName: String) {
        viewModelScope.launch {
            _bookState.update {it.copy(_loading = true)}
            val response =  bookRepo.getByClassification(classificationName)
            _bookState.update { it.copy(listLibro = response) }
            _bookState.update {it.copy(_loading = false)}
        }
    }
}
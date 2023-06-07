package com.example.gestion_sispac.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestion_sispac.ui.theme.model.GenBookItem
import com.example.gestion_sispac.ui.theme.repository.RepositoryBook
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {
    data class UIState(
        val _loading: Boolean = false,
        val listLibro: List<GenBookItem> = emptyList<GenBookItem>()
    )

    val bookRepo : RepositoryBook = RepositoryBook()

    private val _bookState = MutableStateFlow<UIState>(UIState())
    val bookState: StateFlow<UIState> = _bookState

    //nuevo
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _books = MutableStateFlow(listOf<GenBookItem>())
    val books = searchText.combine(_books) {
        text, books ->
        if (text.isBlank())
            books
        else {
            /*books.filter {
                it.doesMatchSearchQuery(text)
            }*/
        }
    }
    .stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),
        _books.value
    )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

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
            //val response :  ArrayList<GenBookItem?> = ArrayList<GenBookItem?>()
            //response.add(bookRepo.getByTitle(title))
            val response = bookRepo.getAll()
            //val test : List<GenBookItem> = response
            _bookState.update { it.copy(listLibro = response) }
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
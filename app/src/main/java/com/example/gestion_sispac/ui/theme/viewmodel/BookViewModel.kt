package com.example.gestion_sispac.ui.theme.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestion_sispac.ui.theme.model.GenBookItem
import com.example.gestion_sispac.ui.theme.repository.RepositoryBook
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
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

    //NUEVO_----------------------------------------
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchBook = MutableStateFlow(listOf<searchBook>())
    val searchBooks = searchText
        .debounce(500L)
        .onEach { _isSearching.update { true } }
        .combine(_searchBook) { text, searchBooks ->
            if(text.isBlank()) {
                searchBooks
            } else {
                delay(2000L)
                searchBooks.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(2000),
            _searchBook.value
        )

    data class searchBook(
        val title: String,
        val isbn : String,
        val publisher_name : String,
        val classification_name : String,
        val authors : List<String>
    )  {
        fun doesMatchSearchQuery(query : String): Boolean {
            val matchingCombinations = listOf(
                "$publisher_name",
                "$title",
                "$classification_name"
            )
            return matchingCombinations.any {
                it.contains(query, ignoreCase = true)
            }
        }
    }

    //actualizar UIstate por busqueda de editorial
    fun onPublisherSearchChange(publisherName: String) {
        viewModelScope.launch {
            _searchText.value = publisherName
            _bookState.update { it.copy(_loading = true) }

            val response = bookRepo.getByPublisher(publisherName)

            _bookState.update { it.copy(listLibro = response) }
            _bookState.update { it.copy(_loading = false) }
        }
    }

    //actualizar UIstate por busqueda de categoria
    fun onClassificationSearchChange(classificationName: String) {
        viewModelScope.launch {
            _searchText.value = classificationName
            _bookState.update { it.copy(_loading = true) }

            val response = bookRepo.getByClassification(classificationName)

            _bookState.update { it.copy(listLibro = response) }
            _bookState.update { it.copy(_loading = false) }
        }
    }

    //actualizar UIstate por busqueda de categoria
    fun onAuthorSearchChange(authorName: String) {
        viewModelScope.launch {
            _searchText.value = authorName
            _bookState.update { it.copy(_loading = true) }

            val response = bookRepo.getByAuthor(authorName)

            _bookState.update { it.copy(listLibro = response) }
            _bookState.update { it.copy(_loading = false) }
        }
    }

    //actualizar UIstate por busqueda de titulo
    fun onTitleSearchChange(title: String) {
        viewModelScope.launch {
            _searchText.value = title
            _bookState.update { it.copy(_loading = true) }

            val response = bookRepo.getByTitle(title)
            //Log.d("try", "$response.toString()")

            _bookState.update { it.copy(listLibro = response) }
            _bookState.update { it.copy(_loading = false) }
        }
    }

    //actualizar UIstate por texfield vacio
    fun onEmptySearchChange() {
        viewModelScope.launch {
            _bookState.update { it.copy(_loading = true) }

            val response = bookRepo.getAll()

            _bookState.update { it.copy(listLibro = response) }
            _bookState.update { it.copy(_loading = false) }
        }
    }

    //------------------------------------------------------------------------

    //initializing the screen (connects to IO thread and displays all books:
    init {
        viewModelScope.launch {
            _bookState.update {it.copy(_loading = true)}
            val response =  bookRepo.getAll()
            _bookState.update { it.copy(listLibro = response) }
            _bookState.update {it.copy(_loading = false)}
        }
    }
}
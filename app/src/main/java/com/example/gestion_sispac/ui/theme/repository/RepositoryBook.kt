package com.example.gestion_sispac.ui.theme.repository

import android.util.Log
import com.example.gestion_sispac.ui.theme.model.Book
import com.example.gestion_sispac.ui.theme.remote.ApiAdapter
import com.example.gestion_sispac.ui.theme.remote.ApiBook

class RepositoryBook {
    private val apiBook : ApiBook = ApiAdapter.getInstance().create(ApiBook::class.java)

    suspend fun getAll(): List<Book> {
        try {
            val bookList = apiBook.getAll()
            return bookList
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return emptyList<Book>()
    }

    suspend fun getByTitle(title: String): Book? {
        try {
            val foundBook = apiBook.getOneByTitle(title)
            return foundBook
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return null
    }

    suspend fun getByPublisher(publisherName: String): List<Book> {
        try {
            val bookList = apiBook.getManyByPublisher(publisherName)
            return bookList
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return emptyList<Book>()
    }

    suspend fun getByClassification(classificationName: String): List<Book> {
        try {
            val bookList = apiBook.getManyByClassification(classificationName)
            return bookList
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return emptyList<Book>()
    }

    suspend fun getByAuthor(authorName: String): List<Book> {
        try {
            val bookList = apiBook.getManyByAuthor(authorName)
            return bookList
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return emptyList<Book>()
    }

}
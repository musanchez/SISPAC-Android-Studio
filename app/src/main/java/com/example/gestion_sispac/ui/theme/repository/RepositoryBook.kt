package com.example.gestion_sispac.ui.theme.repository

import android.util.Log
import com.example.gestion_sispac.ui.theme.model.GenBookItem
import com.example.gestion_sispac.ui.theme.remote.ApiAdapter
import com.example.gestion_sispac.ui.theme.remote.ApiBook

class RepositoryBook {
    private val apiBook : ApiBook = ApiAdapter.getInstance().create(ApiBook::class.java)

    suspend fun getAll(): List<GenBookItem> {
        try {
            val bookList = apiBook.getAll()
            return bookList
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return emptyList<GenBookItem>()
    }

    suspend fun getByTitle(title: String): List<GenBookItem> {
        try {
            val bookList = apiBook.getManyByTitle(title)
            return bookList

        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return emptyList<GenBookItem>()
    }

    suspend fun getByPublisher(publisherName: String): List<GenBookItem>  {
        try {
            val bookList = apiBook.getManyByPublisher(publisherName)
            return bookList
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return emptyList<GenBookItem>()
    }

    suspend fun getByClassification(classificationName: String): List<GenBookItem> {
        try {
            val bookList = apiBook.getManyByClassification(classificationName)
            return bookList
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return emptyList<GenBookItem>()
    }

    suspend fun getByAuthor(authorName: String):  List<GenBookItem> {
        try {
            val bookList = apiBook.getManyByAuthor(authorName)
            return bookList
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return emptyList<GenBookItem>()
    }

}
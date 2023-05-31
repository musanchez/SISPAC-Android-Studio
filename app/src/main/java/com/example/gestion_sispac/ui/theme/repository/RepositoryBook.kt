package com.example.gestion_sispac.ui.theme.repository

import android.util.Log
import com.example.gestion_sispac.ui.theme.model.GenBook
import com.example.gestion_sispac.ui.theme.model.GenBookItem
import com.example.gestion_sispac.ui.theme.remote.ApiAdapter
import com.example.gestion_sispac.ui.theme.remote.ApiBook

class RepositoryBook {
    private val apiBook : ApiBook = ApiAdapter.getInstance().create(ApiBook::class.java)

    suspend fun getAll(): GenBook? {
        try {
            val bookList = apiBook.getAll()
            return bookList
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return null
    }

    suspend fun getByTitle(title: String): GenBookItem? {
        try {
            val foundBook = apiBook.getOneByTitle(title)
            return foundBook
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return null
    }

    suspend fun getByPublisher(publisherName: String): GenBook? {
        try {
            val bookList = apiBook.getManyByPublisher(publisherName)
            return bookList
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return null
    }

    suspend fun getByClassification(classificationName: String): GenBook? {
        try {
            val bookList = apiBook.getManyByClassification(classificationName)
            return bookList
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return null
    }

    suspend fun getByAuthor(authorName: String): GenBook? {
        try {
            val bookList = apiBook.getManyByAuthor(authorName)
            return bookList
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return null
    }

}
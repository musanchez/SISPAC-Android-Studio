package com.example.gestion_sispac.ui.theme.remote

import com.example.gestion_sispac.ui.theme.model.Book
import retrofit2.http.GET

interface ApiBook {
    @GET("/api/Book/all")
    suspend fun getAll(): List<Book>

    @GET("/api/Book/one/bytitle/{isbn}")
    suspend fun getOneByTitle(title: String): Book

    @GET("/api/Book/many/bypublisher/{publisherName}")
    suspend fun getManyByPublisher(publisherName: String): List<Book>

    @GET("/api/Book/one/byclassification/{classificationName}")
    suspend fun getManyByClassification(classificationName: String): List<Book>

    @GET("/api/Book/one/byauthor/{authorName}")
    suspend fun getManyByAuthor(authorName: String): List<Book>
}
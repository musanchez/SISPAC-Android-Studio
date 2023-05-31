package com.example.gestion_sispac.ui.theme.remote

import com.example.gestion_sispac.ui.theme.model.GenBook
import com.example.gestion_sispac.ui.theme.model.GenBookItem
import retrofit2.http.GET

interface ApiBook {
    @GET("/api/Book/all")
    suspend fun getAll(): GenBook

    @GET("/api/Book/one/bytitle/{isbn}")
    suspend fun getOneByTitle(title: String): GenBookItem

    @GET("/api/Book/many/bypublisher/{publisherName}")
    suspend fun getManyByPublisher(publisherName: String): GenBook

    @GET("/api/Book/one/byclassification/{classificationName}")
    suspend fun getManyByClassification(classificationName: String): GenBook

    @GET("/api/Book/one/byauthor/{authorName}")
    suspend fun getManyByAuthor(authorName: String): GenBook
}
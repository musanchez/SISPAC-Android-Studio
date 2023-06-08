package com.example.gestion_sispac.ui.theme.remote

import com.example.gestion_sispac.ui.theme.model.GenBookItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiBook {
    @GET("/api/Book/all")
    suspend fun getAll(): List<GenBookItem>

    @GET("/api/Book/many/bytitle/{title}")
    suspend fun getManyByTitle(@Path("title") title: String): List<GenBookItem>

    @GET("/api/Book/many/bypublisher/{publisherName}")
    suspend fun getManyByPublisher(@Path("publisherName") publisherName: String): List<GenBookItem>

    @GET("/api/Book/many/byclassification/{classificationName}")
    suspend fun getManyByClassification(@Path("classificationName") classificationName: String): List<GenBookItem>

    @GET("/api/Book/many/byauthor/{authorName}")
    suspend fun getManyByAuthor(@Path("authorName") authorName: String): List<GenBookItem>
}
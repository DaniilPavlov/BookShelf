package com.example.bookshelf.network

import com.example.bookshelf.BookShelf
import retrofit2.http.GET
import retrofit2.http.Query

// Данные будут браться по этой ссылке:
// https://www.googleapis.com/books/v1/volumes?q=search+terms

// https://www.googleapis.com/books/ = url
// volumes = запрос, аннотация @GET указывает тип запроса
// q и maxResults = параметры запроса, аннотация @Query

// Функцию booksSearch обозначим словом suspend – это значит, что функцию можно приостанавливать.
// Это нужно для того, чтобы вызывать ее из корутины.
// Корутины Kotlin позволяют выполнять какую-то потенциально долгую работу в отдельных потоках,
// чтобы не нагружать главный поток приложения. Сетевые запросы как раз относятся к тяжелым видам
// работы, и их нельзя выполнять в главном потоке, иначе ваше приложение может зависнуть или даже упасть.

interface BookService {

    @GET("volumes")
    suspend fun bookSearch(
        @Query("q") searchQuery: String,
        @Query("maxResults") maxResults: Int
    ): BookShelf
}
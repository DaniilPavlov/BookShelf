package com.example.bookshelf.data

import com.example.bookshelf.network.BookService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val booksRepository: BooksRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://www.googleapis.com/books/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: BookService by lazy {
        retrofit.create(BookService::class.java)
    }

    // Через делегат by lazy мы лениво инициализируем наш репозиторий и передаем ему в конструкторе ретрофит сервис.
    // Этот способ гарантирует, что экземпляр репозитория будет создан только однажды в процессе работы.
    override val booksRepository: BooksRepository by lazy {
        NetworkBooksRepository(retrofitService)
    }
}
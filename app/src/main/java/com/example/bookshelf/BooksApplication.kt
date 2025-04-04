package com.example.bookshelf

import android.app.Application
import com.example.bookshelf.data.AppContainer
import com.example.bookshelf.data.DefaultAppContainer

class BooksApplication : Application() {
    // Переменная инициализируется только во время вызова onCreate(),
    // поэтому она должна быть помечена модификатором lateinit
    private lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
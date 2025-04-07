package com.example.bookshelf.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BooksApplication
import com.example.bookshelf.data.Book
import com.example.bookshelf.data.BooksRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


// дата класс Success будет хранить список книг,
// синглтоны Error и Loading – ошибка и загрузка.

// Это тип данных, который ViewModel будет передавать интерфейсу, и в зависимости от результата
// эти данные будут иметь вид:
// 1) либо процесса загрузки (Loading)
// 2) либо ошибки (Error) в случае сбоя при загрузке данных
// 3) либо в случае успеха возвратится объект (Success), содержащий список книг.

sealed interface BooksUiState {
    data class Success(val bookSearch: List<Book>) : BooksUiState
    object Error : BooksUiState
    object Loading : BooksUiState
}

class BooksViewModel(
    private val booksRepository: BooksRepository
) : ViewModel() {

    var booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
        private set

    init {
        getBooks()
    }

    fun getBooks(query: String = "book", maxResults: Int = 40) {
        viewModelScope.launch {
            booksUiState = BooksUiState.Loading
            booksUiState =
                try {
                    BooksUiState.Success(booksRepository.getBooks(query, maxResults))
                } catch (_: IOException) {
                    BooksUiState.Error
                } catch (_: HttpException) {
                    BooksUiState.Error
                }
        }
    }

    // Выражение companion object  помогает нам иметь один экземпляр объекта, который используется
    // всеми без необходимости создавать новый экземпляр дорогостоящего объекта. Код внутри выражения
    // создает фабрику ViewModel, которая создает экземпляры BooksViewModel с помощью DI-контейнера,
    // используя экземпляр BooksRepository, полученный из BooksApplication. Factory — это порождающий
    // паттерн проектирования, используемый для создания объектов. Объект BoksViewModel.Factory
    // использует контейнер приложения для извлечения BoоksRepository, а затем передает экземпляр
    // репозитория при создании ViewModel объекта.
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BooksApplication)
                val booksRepository = application.appContainer.booksRepository
                BooksViewModel(booksRepository = booksRepository)
            }
        }
    }
}

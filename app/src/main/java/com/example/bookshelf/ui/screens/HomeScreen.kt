package com.example.bookshelf.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookshelf.data.Book
import com.example.bookshelf.ui.BooksUiState

// Modifier — это объект для настройки внешнего вида и поведения UI-компонентов в Jetpack Compose.
// Он позволяет:
// Задавать размеры, отступы, фон, границы.
// Менять расположение (выравнивание, вес в Row/Column).
// Добавлять обработчики событий (клики, скролл).
// Применять анимации и трансформации.

@Composable
fun HomeScreen(
    booksUiState: BooksUiState,
    retryAction: () -> Unit,
    modifier: Modifier,
    onBookClicked: (Book) -> Unit
) {
    when (booksUiState) {
        is BooksUiState.Loading -> LoadingScreen(modifier)
        is BooksUiState.Success -> BooksGridScreen(
            books = booksUiState.bookSearch,
            modifier = modifier,
            onBookClicked = onBookClicked,
        )
        is BooksUiState.Error -> ErrorScreen(retryAction = retryAction, modifier)
    }
}
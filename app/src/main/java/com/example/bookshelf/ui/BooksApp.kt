package com.example.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.bookshelf.R
import com.example.bookshelf.ui.screens.HomeScreen
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksApp(
    modifier: Modifier = Modifier
) {
    val booksViewModel: BooksViewModel =
        viewModel(factory = BooksViewModel.Factory)


    // Scaffold – это корневая composable функция для размещения всех экранных компонентов.
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer, // фон
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer, // цвет текста
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer // цвет иконок
                )
            )
        }

    ) {
        // Surface – это поверхность для отрисовки компонентов. Surface следует общим шаблонам материального дизайна
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                booksUiState = booksViewModel.booksUiState,
                retryAction = { booksViewModel.getBooks() },
                modifier = modifier
            )
        }
    }
}
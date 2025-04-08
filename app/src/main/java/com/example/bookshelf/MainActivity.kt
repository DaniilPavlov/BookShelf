package com.example.bookshelf

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.example.bookshelf.ui.BooksApp
import com.example.bookshelf.ui.theme.BookShelfTheme
import androidx.core.net.toUri

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookShelfTheme {
                val context = LocalContext.current // Получаем контекст правильно для Compose
                BooksApp(onBookClicked = { book ->
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, book.previewLink?.toUri()).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        // Безопасный запуск активности
                        context.startActivity(intent)
                    } catch (_: ActivityNotFoundException) {
                        // Обработка случая, когда нет подходящего activity для ссылки
                        Toast.makeText(
                            context,
                            "No activity available to open this link",
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: Exception) {
                        // Общая обработка ошибок
                        Log.e("BookShelf", "Error opening link", e)
                    }
                })
            }
        }
    }

}
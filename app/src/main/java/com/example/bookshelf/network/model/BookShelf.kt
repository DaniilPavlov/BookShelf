package com.example.bookshelf

import com.google.gson.annotations.SerializedName

// Сервис, генерирующий все необходимые классы по JSON-ответу сервера:
// JSON to Kotlin Data Class Generator Online
// https://www.json2kt.com/
data class BookShelf (

  @SerializedName("kind"       ) var kind       : String?          = null,
  @SerializedName("totalItems" ) var totalItems : Int?             = null,
  @SerializedName("items"      ) var items      : ArrayList<Items> = arrayListOf()

)
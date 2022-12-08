package com.example.cougarsgo

import androidx.room.Entity
import androidx.room.PrimaryKey

class UserModel(
    var id: Int = 0,
    var username: String="",
    var email: String = "",
    var password: String ="",
    var listingsID: List<Int> = emptyList(),
    var rating: Float = 0F,
) {
    init {

    }
}
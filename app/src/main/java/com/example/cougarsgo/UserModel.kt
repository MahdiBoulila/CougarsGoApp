package com.example.cougarsgo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usersTable")
class UserModel(
    @PrimaryKey
    var id: Int = 0,
    var username: String="",
    var email: String = "",
    var password: String ="",
    var listings: List<ListingModel> = emptyList(),
    var rating: Float = 0F,
) {
    init {

    }
}
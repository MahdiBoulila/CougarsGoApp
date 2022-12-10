package com.example.cougarsgo

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
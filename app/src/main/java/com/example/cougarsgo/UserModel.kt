package com.example.cougarsgo

class UserModel(
    var id: String = "",
    var username: String="",
    var email: String = "",
    var password: String ="",
    var listingsID: ArrayList<String> = ArrayList(),
    var rating: Float = 5F,
) {
    init {

    }
}
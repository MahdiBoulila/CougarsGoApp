package com.cougarsgo.cougarsgo
// @Version 1.2
class UserModel(
    var id: String = "",
    var username: String="",
    var email: String = "",
    var password: String ="",
    var listingsID: ArrayList<String> = ArrayList(),
    var rating: Float = 5F,
) {}
package com.example.cougarsgo

class SellerModel(
    var id: Int = 0,
    var username: String="",
    var password: String ="",
    var listings: List<ListingModel> = emptyList(),
    var rating: Float = 0F,
) {
    init {

    }
}
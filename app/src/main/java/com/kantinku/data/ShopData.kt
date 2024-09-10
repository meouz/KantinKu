package com.kantinku.data

data class ShopData(
    val image: String,
    val name: String,
    val rating: Float,
    val ratingCount: Int,
    val location: String,
    val distance: Int,
    val type: String,
    val cheapest: Boolean,
    val open: Boolean,
    val waitingTime: Int,
    val discount: Int,
    val queue: Int,
    val onProcess: Int,
)
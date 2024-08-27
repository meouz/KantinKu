package com.kantinku.data

data class OrderData(
    val orders: List<FoodData>,
    val quantity: Int,
    val totalPrice: Int,
)

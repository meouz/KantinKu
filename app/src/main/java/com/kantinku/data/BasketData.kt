package com.kantinku.data

data class BasketData(
    val menu: ArrayList<MenuData>,
    val notes: String,
    val waitingTime: String,
    val proceed: String,
    val queue: Int,
    val status: String,
)

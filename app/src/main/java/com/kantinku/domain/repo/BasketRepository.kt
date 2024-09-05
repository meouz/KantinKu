package com.kantinku.domain.repo

import com.kantinku.data.BasketData
import com.kantinku.data.MenuData

interface BasketRepository {
    fun createOrder(
        userId: String,
        data: ArrayList<MenuData>,
    )
    
    fun getData(
        userId: String,
        menumenu: (BasketData) -> Unit,
    )
}
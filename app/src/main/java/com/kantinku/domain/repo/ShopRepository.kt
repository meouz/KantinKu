package com.kantinku.domain.repo

import com.kantinku.data.MenuData

interface ShopRepository {
    fun getMenuUtama(
        shopName: String,
        resp: (ArrayList<MenuData>) -> Unit,
    )
    
    fun getMenuTakHabis(
        shopName: String,
        resp: (ArrayList<MenuData>) -> Unit,
    )
    
    fun getMenuBest(
        shopName: String,
        resp: (ArrayList<MenuData>) -> Unit,
    )
}
package com.kantinku.domain.repo

import com.kantinku.data.ShopData

interface ExploreRepository {
    fun getShop(
        shops: (ArrayList<ShopData>) -> Unit,
    )
}
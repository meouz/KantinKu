package com.kantinku.domain.repo

import com.kantinku.data.FoodData
import com.kantinku.data.ShopData

interface ExploreRepository {
    fun getShopData(
        shops: (ArrayList<ShopData>) -> Unit,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
    )
    
    fun getShopMenus(
        menus: List<FoodData>,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
    )
}
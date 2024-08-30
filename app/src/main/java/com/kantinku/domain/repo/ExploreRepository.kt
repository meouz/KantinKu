package com.kantinku.domain.repo

import com.kantinku.data.MarketData

interface ExploreRepository {
    fun getMarkets(
        markets: (ArrayList<MarketData>) -> Unit,
    )
}
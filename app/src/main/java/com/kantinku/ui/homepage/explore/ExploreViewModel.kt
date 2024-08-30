package com.kantinku.ui.homepage.explore

import androidx.lifecycle.ViewModel
import com.kantinku.data.MarketData
import com.kantinku.domain.usecase.ExploreRepositoryImpl

class ExploreViewModel: ViewModel() {
    private val repository: ExploreRepositoryImpl = ExploreRepositoryImpl()
    private val markets: List<MarketData> = ArrayList()
    
    fun postMarkets(onComplete: () -> Unit) {
        repository.getMarkets {
            (markets as ArrayList).addAll(it)
            onComplete()
        }
    }
    
    fun getMarkets(): List<MarketData> {
        return markets
    }
}
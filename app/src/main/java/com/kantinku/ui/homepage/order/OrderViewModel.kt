package com.kantinku.ui.homepage.order

import androidx.lifecycle.ViewModel
import com.kantinku.data.BasketData
import com.kantinku.data.MenuData
import com.kantinku.domain.usecase.BasketRepositoryImpl

class OrderViewModel : ViewModel() {
    private val repository: BasketRepositoryImpl = BasketRepositoryImpl()
    private val data: List<MenuData> = ArrayList()
    private val basket: List<BasketData> = ArrayList()
    
    fun postData(userId: String, onComplete: () -> Unit) {
        repository.getData(userId) {
            (data as ArrayList).addAll(it.menu)
            (basket as ArrayList).addAll(listOf(it))
            onComplete()
        }
    }
    
    fun getData(): List<MenuData> {
        return data
    }
    
    fun getBasket(): List<BasketData> {
        return basket
    }
    
    fun updateStatus(basketData: BasketData) {
        repository.updateStatus(basketData)
    }
}
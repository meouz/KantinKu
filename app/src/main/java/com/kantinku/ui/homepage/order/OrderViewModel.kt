package com.kantinku.ui.homepage.order

import androidx.lifecycle.ViewModel
import com.kantinku.data.MenuData
import com.kantinku.domain.usecase.BasketRepositoryImpl

class OrderViewModel : ViewModel() {
    private val repository: BasketRepositoryImpl = BasketRepositoryImpl()
    private val data: List<MenuData> = ArrayList()
    
    fun postData(userId: String, onComplete: () -> Unit) {
        repository.getData(userId) {
            (data as ArrayList).addAll(it.menu)
            onComplete()
        }
    }
    
    fun getData(): List<MenuData> {
        return data
    }
}
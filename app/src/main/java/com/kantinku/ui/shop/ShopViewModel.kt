package com.kantinku.ui.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kantinku.data.MenuData
import com.kantinku.domain.usecase.ShopRepositoryImpl

class ShopViewModel : ViewModel() {
    private val repository: ShopRepositoryImpl = ShopRepositoryImpl()
    private val _data: MutableLiveData<List<MenuData>> = MutableLiveData()
    val data: LiveData<List<MenuData>> get() = _data
    
    private var bestMenus: List<MenuData> = listOf()
    private var menus: List<MenuData> = listOf()
    private var notFinishedMenus: List<MenuData> = listOf()
    
    fun setMenus(shopName: String, onComplete: () -> Unit) {
        repository.getMenus(shopName) {
            _data.value = it
            bestMenus = it.filter { menu -> menu.quality == 1 }
            menus = it.filter { menu -> menu.quality == 2 }
            notFinishedMenus = it.filter { menu -> menu.quality == 3 }
            onComplete()
        }
    }
    
    fun getBestMenus(): List<MenuData> {
            return bestMenus
    }
    
    fun getMenus(): List<MenuData> {
        return menus
    }
    
    fun getNotFinishedMenus(): List<MenuData> {
        return notFinishedMenus
    }
}
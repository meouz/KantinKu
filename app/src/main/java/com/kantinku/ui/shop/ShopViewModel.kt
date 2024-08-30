package com.kantinku.ui.shop

import androidx.lifecycle.ViewModel
import com.kantinku.data.MenuData
import com.kantinku.domain.usecase.ShopRepositoryImpl
import com.kantinku.ui.shop.ShopViewModel.ShopViewModel.items

class ShopViewModel : ViewModel() {
    private val repository: ShopRepositoryImpl = ShopRepositoryImpl()
    private val menusBest: List<MenuData> = ArrayList()
    private val menusUtama: List<MenuData> = ArrayList()
    private val menusTakHabis: List<MenuData> = ArrayList()
    
    object ShopViewModel {
        var items = mutableListOf<ArrayList<MenuData>>()
        var order = ArrayList<MenuData>()
        var orders = mutableListOf(order)
    }
    
    fun postMenuUtama(shopName: String, onComplete: () -> Unit) {
        repository.getMenuUtama(shopName) {
            (menusUtama as ArrayList).addAll(it)
            (items as ArrayList).addAll(listOf(it))
            onComplete()
        }
    }
    
    fun postMenuTakHabis(shopName: String, onComplete: () -> Unit) {
        repository.getMenuTakHabis(shopName) {
            (menusTakHabis as ArrayList).addAll(it)
            (items as ArrayList).addAll(listOf(it))
            onComplete()
        }
    }
    
    fun postMenuBest(shopName: String, onComplete: () -> Unit) {
        repository.getMenuBest(shopName) {
            (menusBest as ArrayList).addAll(it)
            (items as ArrayList).addAll(listOf(it))
            onComplete()
        }
    }
    
    fun getMenuUtama(): List<MenuData> {
        return menusUtama
    }
    
    fun getMenuTakHabis(): List<MenuData> {
        return menusTakHabis
    }
    
    fun getMenuBest(): List<MenuData> {
        return menusBest
    }
}
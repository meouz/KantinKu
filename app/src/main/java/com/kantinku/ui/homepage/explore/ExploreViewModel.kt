package com.kantinku.ui.homepage.explore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kantinku.data.ShopData
import com.kantinku.domain.usecase.ExploreRepositoryImpl

class ExploreViewModel : ViewModel() {
    private val repository: ExploreRepositoryImpl = ExploreRepositoryImpl()
    private val _shops: MutableLiveData<List<ShopData>> = MutableLiveData()
    private val shops: LiveData<List<ShopData>> get() = _shops
    
    fun setShops(onComplete: () -> Unit) {
        if (_shops.value != null) {
            Log.d("ExploreViewModel", "Shops already loaded")
            return
        }
        repository.getShop {
            _shops.value = it
            onComplete()
        }
    }
    
    fun getShops(): LiveData<List<ShopData>> {
        return shops
    }
}
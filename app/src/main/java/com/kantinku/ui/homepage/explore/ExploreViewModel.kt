package com.kantinku.ui.homepage.explore

import androidx.lifecycle.ViewModel
import com.kantinku.domain.usecase.ExploreRepositoryImpl

class ExploreViewModel: ViewModel() {
    private val repository: ExploreRepositoryImpl = ExploreRepositoryImpl()
    
//    fun getShops() {
//        repository.getShopData(
//            shops = { shopsData ->
//                // Do something with the data
//                movieList.clear()
//                movieList.addAll(dataList)
//                adapter.notifyDataSetChanged()
//            },
//            onSuccess = {
//                // Do something on success
//            },
//            onFailure = {
//                // Do something on failure
//            }
//        )
//    }
}
package com.kantinku.ui.homepage.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kantinku.domain.usecase.HistoryRepositoryImpl

class HistoryViewModel : ViewModel() {
    private val repository = HistoryRepositoryImpl()
    private var data: LiveData<List<String>> = MutableLiveData()
    
    fun getData(): LiveData<List<String>> {
        return data
    }
    
    fun setData(onComplete: () -> Unit) {
        (data as MutableLiveData).value = repository.getHistory()
    }
}
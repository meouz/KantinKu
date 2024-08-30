package com.kantinku.ui.auth

import androidx.lifecycle.ViewModel
import com.kantinku.data.UserData
import com.kantinku.domain.usecase.AuthRepositoryImpl

class RegisterViewModel : ViewModel() {
    
    private val repository = AuthRepositoryImpl()
    
    fun register(user: UserData, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        repository.register(user, {
            onSuccess("Register Success")
        }, {
            onFailure("Register Failed")
        })
    }
}
package com.kantinku.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kantinku.data.User
import com.kantinku.domain.usecase.AuthRepositoryImpl

class RegisterViewModel : ViewModel() {
    
    private val repository = AuthRepositoryImpl()
    
    fun register(user: User, confirmPassword: String) {
        if (user.password != confirmPassword) {
            Log.d("RegisterViewModel", "Password and Confirm Password do not match")
            return
        }
        
        repository.register(user, {
            Log.d("RegisterViewModel", "Register Success")
        }, {
            Log.d("RegisterViewModel", "Register Failed")
        })
    }
}
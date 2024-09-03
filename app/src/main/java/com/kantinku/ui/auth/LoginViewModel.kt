package com.kantinku.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kantinku.data.UserData
import com.kantinku.domain.repo.LoginRepository
import com.kantinku.domain.usecase.LoginRepositoryImpl

class LoginViewModel : ViewModel() {
    private val repository: LoginRepositoryImpl = LoginRepositoryImpl()
    
    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean> get() = _loginStatus
    
    fun login(email: String, password: String) {
        // Login the user
        repository.login(UserData(email, password), {
            // On success
            _loginStatus.postValue(true)
        }, {
            // On failure
            _loginStatus.postValue(false)
        })
    }
}
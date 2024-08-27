package com.kantinku.domain.repo

import com.kantinku.data.UserData

interface AuthRepository {
    fun login(
        user: UserData,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
    )
    
    fun register(
        user: UserData,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
    )
}
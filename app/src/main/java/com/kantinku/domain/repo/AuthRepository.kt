package com.kantinku.domain.repo

import com.kantinku.data.User

interface AuthRepository {
    fun login(user: User, onSuccess: () -> Unit, onFailure: () -> Unit)
    fun register(user: User, onSuccess: () -> Unit, onFailure: () -> Unit)
}
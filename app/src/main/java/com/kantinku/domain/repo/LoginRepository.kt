package com.kantinku.domain.repo

import com.kantinku.data.UserData

interface LoginRepository {
    fun login(
        user: UserData,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
    )
}
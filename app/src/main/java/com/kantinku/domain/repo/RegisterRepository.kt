package com.kantinku.domain.repo

import com.kantinku.data.UserData

interface RegisterRepository {
    fun register(
        user: UserData,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
    )
}
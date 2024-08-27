package com.kantinku.domain.repo

interface ForgotPassRepository {
    fun sendPasswordResetEmail(
        email: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
    )
}
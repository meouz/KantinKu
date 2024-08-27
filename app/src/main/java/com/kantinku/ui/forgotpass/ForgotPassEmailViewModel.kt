package com.kantinku.ui.forgotpass

import androidx.lifecycle.ViewModel
import com.kantinku.domain.usecase.ForgotPassRepositoryImpl

class ForgotPassEmailViewModel : ViewModel() {
    private val repository: ForgotPassRepositoryImpl = ForgotPassRepositoryImpl()
    
    fun sendEmail(email: String) {
        // Send email to the user
        repository.sendPasswordResetEmail(email, {
            // On success
        }, {
            // On failure
            throw Exception("Failed to send email")
        })
    }
}
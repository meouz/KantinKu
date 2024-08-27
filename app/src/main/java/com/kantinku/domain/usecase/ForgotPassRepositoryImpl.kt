package com.kantinku.domain.usecase

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.kantinku.domain.repo.ForgotPassRepository

class ForgotPassRepositoryImpl : ForgotPassRepository {
    override fun sendPasswordResetEmail(
        email: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
    ) {
        Firebase.auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                    onSuccess()
                } else {
                    Log.d(TAG, "Email not sent.")
                    onFailure()
                }
            }
    }
}
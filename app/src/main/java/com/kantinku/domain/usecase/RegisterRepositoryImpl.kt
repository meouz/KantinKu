package com.kantinku.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import com.kantinku.data.UserData
import com.kantinku.domain.repo.RegisterRepository

class RegisterRepositoryImpl : RegisterRepository {
    private var auth = FirebaseAuth.getInstance()
    
    override fun register(user: UserData, onSuccess: () -> Unit, onFailure: () -> Unit) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure()
                }
            }
    }
}
package com.kantinku.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import com.kantinku.data.UserData
import com.kantinku.domain.repo.LoginRepository

class LoginRepositoryImpl : LoginRepository {
    private var auth = FirebaseAuth.getInstance()
    
    override fun login(user: UserData, onSuccess: () -> Unit, onFailure: () -> Unit) {
        auth.signInWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure()
                }
            }
    }
}
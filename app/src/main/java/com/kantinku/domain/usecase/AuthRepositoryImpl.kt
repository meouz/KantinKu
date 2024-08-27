package com.kantinku.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.kantinku.data.UserData
import com.kantinku.domain.repo.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    private var auth = FirebaseAuth.getInstance()
    private lateinit var db: DatabaseReference
    
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

//    private fun createUsername(username: String) {
//        db = FirebaseDatabase.getInstance().reference
//        db.child("users").child(auth.currentUser?.uid.toString()).child("username")
//            .setValue(username)
//    }

}
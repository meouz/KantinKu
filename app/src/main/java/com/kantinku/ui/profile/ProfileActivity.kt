package com.kantinku.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kantinku.R
import com.kantinku.databinding.ActivityProfileBinding
import com.kantinku.ui.auth.LoginActivity
import com.kantinku.ui.homepage.HomeActivity
import com.kantinku.utils.PrefManager

class ProfileActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityProfileBinding
    private val binding get() = _binding
    private lateinit var prefManager: PrefManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        prefManager = PrefManager(this)
        
        binding.btnBack.setOnClickListener {
            finish()
        }
        
        binding.btnOrder.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        
        val user = Firebase.auth.currentUser
        user?.let {
            val name = it.displayName
            val email = it.email
            val photoUrl = it.photoUrl
            
            binding.tvUsername.text = name
            binding.tvEmail.text = email
            Glide.with(this).load(photoUrl).into(binding.ivUser)
        }
        
        binding.btnLogOut.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.bottom_sheet_logout, null)
            
            val btnAccept = view.findViewById<TextView>(R.id.btnAccept)
            val btnCancel = view.findViewById<TextView>(R.id.btnCancel)
            
            btnAccept.setOnClickListener {
                signOutUser()
                dialog.dismiss()
            }
            
            btnCancel.setOnClickListener {
                dialog.dismiss()
            }
            
            dialog.setCancelable(true)
            dialog.setContentView(view)
            dialog.show()
        }
    }
    
    private fun signOutUser() {
        // Clear user session data (e.g., shared preferences, database, etc.)
        Firebase.auth.signOut()
        val sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
        prefManager.clear()
        
        // Sign out from Google
        GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN).signOut().addOnCompleteListener {
            Log.d("ProfileActivity", "signOutUser: User signed out")
        }
        
        Log.d("ProfileActivity", "signOutUser: User signed out")
        
        // Navigate to the login screen
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        
        prefManager.setNew(false)
        finish()
    }
}
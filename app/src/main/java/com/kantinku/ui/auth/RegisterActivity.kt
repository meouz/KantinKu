package com.kantinku.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.kantinku.R
import com.kantinku.data.User
import com.kantinku.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityRegisterBinding
    private val binding get() = _binding
    private val viewModel = RegisterViewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.btnLogin.setOnClickListener{
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            
            val user = User(email, password)
            Log.d("RegisterActivity", user.toString())
            
            viewModel.register(user, password)
        }
    }
}
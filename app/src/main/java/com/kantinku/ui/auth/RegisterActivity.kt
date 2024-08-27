package com.kantinku.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.kantinku.data.UserData
import com.kantinku.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityRegisterBinding
    private val binding get() = _binding
    private val viewModel = RegisterViewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.btnRegister.setOnClickListener {
            // Get the email, password, and confirm password
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()
            
            // validate user input
            if (!isEmailValid(email)) {
                return@setOnClickListener
            }
            
            if (!isPasswordValid(password)) {
                return@setOnClickListener
            }
            
            if (!isSame(password, confirmPassword)) {
                return@setOnClickListener
            }
            
            // register user
            val user = UserData(email, password)
            viewModel.register(user, password)
        }
        
        binding.tvGoLogin.setOnClickListener {
            // Go to login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    
    private fun isEmailValid(email: String): Boolean {
        return email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    
    private fun isPasswordValid(password: String): Boolean {
        return password.length < 8 && password.isEmpty()
    }
    
    private fun isSame(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}
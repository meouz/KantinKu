package com.kantinku.ui.forgotpass

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kantinku.databinding.ActivityForgotPassEmailBinding
import com.kantinku.ui.auth.LoginActivity

class ForgotPassEmailActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityForgotPassEmailBinding
    private val binding get() = _binding
    private lateinit var viewModel: ForgotPassEmailViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        _binding = ActivityForgotPassEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        viewModel = ForgotPassEmailViewModel()
        
        binding.btnSend.setOnClickListener {
            val email = binding.etEmail.text.toString()
            if (isEmailValid(email)) {
                binding.etEmail.error = "Email is not valid"
                return@setOnClickListener
            }
            
            try {
                viewModel.sendEmail(email)
                message("Email sent")
            } catch (e: Exception) {
                message(e.message.toString())
            }
        }
        
        binding.btnBack.setOnClickListener {
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }
    }
    
    private fun isEmailValid(email: String): Boolean {
        return email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    
    private fun message(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
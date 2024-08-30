package com.kantinku.ui.forgotpass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kantinku.R
import com.kantinku.databinding.ActivityForgotPassEmailBinding
import com.kantinku.databinding.ActivityForgotPassNewPassBinding

class ForgotPassNewPassActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityForgotPassNewPassBinding
    private val binding get() = _binding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityForgotPassNewPassBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
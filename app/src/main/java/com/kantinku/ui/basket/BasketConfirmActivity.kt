package com.kantinku.ui.basket

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kantinku.databinding.ActivityBasketConfirmBinding
import com.kantinku.ui.homepage.HomeActivity

class BasketConfirmActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityBasketConfirmBinding
    private val binding get() = _binding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBasketConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.tvSelesai.setOnClickListener {
            Intent(this, HomeActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }
        
        binding.goOrderPage.setOnClickListener {
            Intent(this, HomeActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }
    }
}
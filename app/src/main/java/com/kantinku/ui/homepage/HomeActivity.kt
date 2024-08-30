package com.kantinku.ui.homepage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kantinku.R
import com.kantinku.databinding.ActivityHomeBinding
import com.kantinku.ui.homepage.explore.ExploreFragment
import com.kantinku.ui.homepage.history.HistoryFragment
import com.kantinku.ui.homepage.order.OrderFragment

class HomeActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityHomeBinding
    private lateinit var bottomNav: BottomNavigationView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        
        setContentView(binding.root)
        
        loadFragment(ExploreFragment())
        
        bottomNav = findViewById(R.id.bottomNav)!!
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.explore -> {
                    loadFragment(ExploreFragment())
                    true
                }
                R.id.order -> {
                    loadFragment(OrderFragment())
                    true
                }
                R.id.receipt -> {
                    loadFragment(HistoryFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
    
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}
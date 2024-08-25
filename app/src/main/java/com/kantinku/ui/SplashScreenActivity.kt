package com.kantinku.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.kantinku.R
import com.kantinku.ui.auth.LoginActivity


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.ANIMATION_CHANGED,
            WindowManager.LayoutParams.ANIMATION_CHANGED);
        supportActionBar?.hide()
        
        setContentView(R.layout.activity_splash_screen)
        
        Handler().postDelayed({
            window.setWindowAnimations(android.R.style.Animation_Toast)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2500)
    }
}
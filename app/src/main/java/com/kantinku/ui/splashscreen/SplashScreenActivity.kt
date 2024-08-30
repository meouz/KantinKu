package com.kantinku.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.kantinku.R
import com.kantinku.ui.auth.LoginActivity
import com.kantinku.ui.onboarding.OnboardingActivity
import com.kantinku.utils.PrefManager

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var prefManager: PrefManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        setContentView(R.layout.activity_splash_screen)
        
        prefManager = PrefManager(this)
        val text = findViewById<TextView>(R.id.textView)
        val splashScreen = findViewById<ConstraintLayout>(R.id.splash_screen)
        
        Handler().postDelayed({
            findViewById<ImageView>(R.id.imageView2).animate().alpha(1f).duration = 500
        }, 1000)
        
        Handler().postDelayed({
            val mSlideLeft = Slide()
            mSlideLeft.slideEdge = Gravity.START
            TransitionManager.beginDelayedTransition(splashScreen, mSlideLeft)
            text.visibility = View.VISIBLE
        }, 2000)
        
        Handler().postDelayed({
            if (prefManager.isNew()) {
                startActivity(Intent(this, OnboardingActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 3000)
    }
    
    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}
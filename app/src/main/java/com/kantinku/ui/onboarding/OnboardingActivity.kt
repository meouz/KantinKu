package com.kantinku.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.kantinku.R
import com.kantinku.databinding.ActivityOnboardingBinding
import com.kantinku.ui.auth.LoginActivity
import com.kantinku.ui.auth.RegisterActivity
import com.kantinku.utils.PrefManager
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator


class OnboardingActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityOnboardingBinding
    private val binding get() = _binding
    private lateinit var images: List<Int>
    private lateinit var text: List<String>
    private lateinit var desc: List<String>
    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: OnboardingAdapter
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var prefManager: PrefManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        prefManager = PrefManager(this)
        viewPager = binding.viewPager
        dotsIndicator = binding.dotsIndicator
        
        images = ArrayList()
        images = images + R.drawable.hello_cuate
        images = images + R.drawable.lunch_time_cuate_1
        images = images + R.drawable.eating_a_variety_of_foods_cuate_1
        
        text = ArrayList()
        text = text + "Selamat Datang di KantinKu"
        text = text + "Pilih, Pesan, Nikmati"
        text = text + "Hemat dan Kurangi Food Waste"
        
        desc = ArrayList()
        desc =
            desc + "Pesan makanan favoritmu tanpa antri. Nikmati kemudahan dan kenyamanan baru setiap hari!"
        desc =
            desc + "Temukan menu favoritmu dan pesan dengan cepat. Cek notifikasi saat siap diambil!"
        desc =
            desc + "Dapatkan makanan berkualitas dengan harga terjangkau. Kurangi food waste dan hemat!"
        
        viewPagerAdapter = OnboardingAdapter(this, images, text, desc)
        viewPager.adapter = viewPagerAdapter
        dotsIndicator.attachTo(viewPager)
        viewPager.setOnTouchListener { v, event -> true }
        
        
        binding.btnLogIn.setOnClickListener {
            prefManager.setNew(false)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        
        binding.btnRegister.setOnClickListener {
            prefManager.setNew(false)
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        
        binding.btnNext.setOnClickListener {
            viewPager.currentItem++
            if (viewPager.currentItem == 2) {
                binding.btnRegister.visibility = View.VISIBLE
                binding.btnLogIn.visibility = View.VISIBLE
                binding.btnNext.visibility = View.GONE
                binding.btnSkip.visibility = View.GONE
            }
        }
        
        binding.btnSkip.setOnClickListener {
            viewPager.currentItem = 2
            binding.btnRegister.visibility = View.VISIBLE
            binding.btnLogIn.visibility = View.VISIBLE
            binding.btnNext.visibility = View.GONE
            binding.btnSkip.visibility = View.GONE
        }
    }
}

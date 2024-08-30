package com.kantinku.ui.shop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kantinku.R
import com.kantinku.data.MenuData
import com.kantinku.databinding.ActivityShopBinding
import com.kantinku.ui.basket.BasketActivity
import com.kantinku.ui.shop.ShopViewModel.ShopViewModel.items
import com.kantinku.ui.shop.ShopViewModel.ShopViewModel.order
import com.kantinku.ui.shop.component.BestSellerAdapter
import com.kantinku.ui.shop.component.MenusAdapter
import com.kantinku.ui.shop.component.NotFinishedAdapter

class ShopActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityShopBinding
    private val binding get() = _binding
    private val viewModel: ShopViewModel = ShopViewModel()
    private lateinit var rvMenus: RecyclerView
    private lateinit var rvBestSeller: RecyclerView
    private lateinit var rvNotFinished: RecyclerView
    private var totalPrice = 0
    
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        _binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val shopName = intent.getStringExtra("shopName") ?: ""
        
        Glide.with(this)
            .load(intent.getStringExtra("image"))
            .placeholder(R.drawable.button)
            .into(binding.ivShop)
        binding.shopName.text = shopName
        binding.tvCategory.text = intent.getStringExtra("category")
        binding.ratingCount.text = intent.getFloatExtra("ratingCount", 0.0f).toString()
        binding.rating.text = intent.getFloatExtra("rating", 0.0f).toString()
        binding.tvDistance.text = intent.getStringExtra("distance")
        binding.tvLocation.text = intent.getStringExtra("location")
        
        binding.likeCount.text = intent.getIntExtra("likeCount", 0).toString()
        
        binding.btnBack.setOnClickListener {
            finish()
        }
        
        rvMenus = binding.rvMenu
        rvBestSeller = binding.rvBestSeller
        rvNotFinished = binding.rvNotFinished
        
        rvMenus.adapter = MenusAdapter(viewModel.getMenuUtama())
        rvBestSeller.adapter = BestSellerAdapter(viewModel.getMenuBest())
        rvNotFinished.adapter = NotFinishedAdapter(viewModel.getMenuTakHabis())
        
        rvMenus.layoutManager = LinearLayoutManager(this)
        rvBestSeller.layoutManager = GridLayoutManager(this, 2)
        rvNotFinished.layoutManager = LinearLayoutManager(this)
        
        (rvMenus.adapter as MenusAdapter).setOnItemClickListener {
            updateBasket(order)
        }
        
        (rvNotFinished.adapter as NotFinishedAdapter).setOnItemClickListener {
            updateBasket(order)
        }
        
        (rvBestSeller.adapter as BestSellerAdapter).setOnItemClickListener {
            updateBasket(order)
        }
        
        viewModel.postMenuBest(shopName) {
            rvBestSeller.adapter?.notifyDataSetChanged()
        }
        
        viewModel.postMenuUtama(shopName) {
            rvMenus.adapter?.notifyDataSetChanged()
        }
        
        viewModel.postMenuTakHabis(shopName) {
            rvNotFinished.adapter?.notifyDataSetChanged()
        }
        
        binding.cardBasketContent.setOnClickListener {
            
            val stash = intent
            intent = Intent(this, BasketActivity::class.java)
            intent.putExtra("shopName", stash.getStringExtra("shopName"))
            intent.putExtra("order", order)
            
            startActivity(intent)
        }
    }
    
    private fun updateBasket(
        data: ArrayList<MenuData>,
    ) {
        totalPrice = 0
        var quantity = 0
        if (data.isEmpty()) {
            binding.cardBasket.visibility = View.GONE
            return
        }
        
        data.forEach { e ->
            quantity += e.quantity
            totalPrice += e.quantity * e.price
        }
        
        if (quantity == 0) {
            binding.cardBasket.visibility = View.GONE
            return
        }
        binding.cardBasket.visibility = View.VISIBLE
        binding.tvItemCount.text = quantity.toString() + " item"
        binding.tvTotal.text = totalPrice.toString()
    }
    
    fun increment(category: Int, index: Int) {
        val item = items[category - 1][index]
        val existingItem = order.find { it == item }
        
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            item.quantity = 1
            order.add(item)
        }
    }
    
    fun decrement(category: Int, index: Int) {
        val item = items[category - 1][index]
        val existingItem = order.find { it == item }
        
        if (existingItem != null) {
            existingItem.quantity--
            
            if (existingItem.quantity == 0) {
                order.remove(existingItem)
            }
        }
    }
    
    override fun onStart() {
        super.onStart()
        order.clear()
        updateBasket(order)
    }
}
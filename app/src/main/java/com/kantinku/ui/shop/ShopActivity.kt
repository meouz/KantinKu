package com.kantinku.ui.shop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kantinku.R
import com.kantinku.data.MenuData
import com.kantinku.databinding.ActivityShopBinding
import com.kantinku.ui.basket.BasketActivity
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
    
    private val order = MutableLiveData<List<MenuData>>()
    
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        _binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val shopName = intent.getStringExtra("name") ?: ""
        
        Glide.with(this)
            .load(intent.getStringExtra("image"))
            .placeholder(R.drawable.button)
            .into(binding.ivShop)
        binding.shopName.text = shopName
        binding.tvCategory.text = intent.getStringExtra("type")
        binding.ratingCount.text = intent.getFloatExtra("ratingCount", 0F).toString()
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
        
        rvMenus.adapter = MenusAdapter(viewModel.getMenus())
        rvBestSeller.adapter = BestSellerAdapter(viewModel.getBestMenus())
        rvNotFinished.adapter = NotFinishedAdapter(viewModel.getNotFinishedMenus())
        
        rvMenus.layoutManager = LinearLayoutManager(this)
        rvBestSeller.layoutManager = GridLayoutManager(this, 2)
        rvNotFinished.layoutManager = LinearLayoutManager(this)
        
        (rvBestSeller.adapter as BestSellerAdapter).setOnItemClickListener {
            updateBasket(order)
        }
        
        (rvMenus.adapter as MenusAdapter).setOnItemClickListener {
            updateBasket(order)
        }
        
        (rvNotFinished.adapter as NotFinishedAdapter).setOnItemClickListener {
            updateBasket(order)
        }
        
        viewModel.setMenus(shopName) {
            rvBestSeller.adapter?.notifyDataSetChanged()
            rvMenus.adapter?.notifyDataSetChanged()
            rvNotFinished.adapter?.notifyDataSetChanged()
        }
        
        binding.cardBasketContent.setOnClickListener {
            val stash = intent
            intent = Intent(this, BasketActivity::class.java)
            intent.putExtra("shopName", stash.getStringExtra("shopName"))
            startActivity(intent)
        }
    }
    
    private fun updateBasket(
        data: MutableLiveData<List<MenuData>>,
    ) {
        totalPrice = 0
        var quantity = 0
        if (data.value?.isEmpty() == true) {
            binding.cardBasket.visibility = View.GONE
            return
        }
        
        data.value!!.forEach { e ->
            quantity += e.quantity
            totalPrice += e.quantity * e.price
        }
        
        if (quantity == 0) {
            binding.cardBasket.visibility = View.GONE
            return
        }
        
        binding.cardBasket.visibility = View.VISIBLE
        binding.tvItemCount.text = "$quantity item"
        binding.tvTotal.text = totalPrice.toString()
    }
    
    fun increment(index: Int) {
        val newOrder = viewModel.data.value?.get(index) ?: return
        val myOrder = order.value?.toMutableList() ?: mutableListOf()
        val orderExist = myOrder.find { it == newOrder }
        
        if (orderExist != null) {
            orderExist.quantity++
        } else {
            newOrder.quantity++
            myOrder.add(newOrder)
        }
        
        order.value = myOrder
    }
    
    fun decrement(index: Int) {
        val newOrder = viewModel.data.value?.get(index) ?: return
        val myOrder = order.value?.toMutableList() ?: mutableListOf()
        val orderExist = myOrder.find { it == newOrder }
        
        if (orderExist != null) {
            orderExist.quantity--
        } else {
            newOrder.quantity--
            myOrder.remove(newOrder)
        }
        
        order.value = myOrder
    }
    
    override fun onStart() {
        super.onStart()
        updateBasket(order)
    }
}
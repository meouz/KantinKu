package com.kantinku.ui.homepage.explore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.kantinku.R
import com.kantinku.ui.homepage.explore.component.MarketAdapter
import com.kantinku.ui.homepage.explore.component.PromotionAdapter
import com.kantinku.ui.homepage.explore.component.RecShopAdapter
import com.kantinku.ui.profile.ProfileActivity
import com.kantinku.ui.shop.ShopActivity
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class ExploreFragment : Fragment() {
    private lateinit var _binding: View
    private val binding get() = _binding
    private lateinit var images: List<Int>
    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: PromotionAdapter
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var recommendationRecyclerView: RecyclerView
    private lateinit var marketRecyclerView: RecyclerView
    private var viewModel: ExploreViewModel = ExploreViewModel()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = inflater.inflate(R.layout.fragment_explore, container, false)
        
        binding.findViewById<ImageView>(R.id.ivProfile).setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }
        
        // setting ads
        viewPager = binding.findViewById(R.id.viewPager)
        dotsIndicator = binding.findViewById(R.id.dotsIndicator)
        
        images = ArrayList()
        images = images + R.drawable.banner
        images = images + R.drawable.banner
        images = images + R.drawable.banner_template
        images = images + R.drawable.banner_template
        
        viewPagerAdapter = PromotionAdapter(requireContext(), images)
        viewPager.adapter = viewPagerAdapter
        dotsIndicator.attachTo(viewPager)
        
        
        // setting market and recommendation
        recommendationRecyclerView = binding.findViewById(R.id.rvRecommendation)
        marketRecyclerView = binding.findViewById(R.id.rvMarket)
        
        recommendationRecyclerView.adapter = viewModel.getShops().value?.let { RecShopAdapter(it) }
        marketRecyclerView.adapter = viewModel.getShops().value?.let { MarketAdapter(it) }
        
        recommendationRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        marketRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        
        (marketRecyclerView.adapter as MarketAdapter).setOnItemClickListener { item ->
            val intent = Intent(requireContext(), ShopActivity::class.java)
            intent.putExtra("image", item.image)
            intent.putExtra("name", item.name)
            intent.putExtra("type", item.type)
            intent.putExtra("ratingCount", item.ratingCount)
            intent.putExtra("rating", item.rating)
            
            intent.putExtra("distance", item.distance)
            intent.putExtra("location", item.location)
            intent.putExtra("id", item.toString())
            Log.d("ExploreFragment", "ID: $item")
            startActivity(intent)
        }
        (recommendationRecyclerView.adapter as RecShopAdapter).setOnItemClickListener { item ->
            val intent = Intent(requireContext(), ShopActivity::class.java)
            intent.putExtra("image", item.image)
            intent.putExtra("name", item.name)
            intent.putExtra("type", item.type)
            intent.putExtra("ratingCount", item.ratingCount)
            intent.putExtra("rating", item.rating)
            
            intent.putExtra("waitingTime", item.waitingTime)
            intent.putExtra("discount", item.discount)
            
            intent.putExtra("distance", item.distance)
            intent.putExtra("location", item.location)
            startActivity(intent)
        }
        
        
        // Call data from view model
        viewModel.setShops {
            marketRecyclerView.adapter?.notifyDataSetChanged()
            recommendationRecyclerView.adapter?.notifyDataSetChanged()
        }
        
        return binding
    }
}
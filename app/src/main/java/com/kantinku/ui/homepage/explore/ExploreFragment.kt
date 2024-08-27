package com.kantinku.ui.homepage.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.kantinku.R
import com.kantinku.ui.homepage.explore.component.PromotionAdapter
import com.kantinku.ui.homepage.explore.component.RecommendationAdapter
import com.kantinku.ui.homepage.explore.component.ShopAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class ExploreFragment : Fragment() {
    private lateinit var _binding: View
    private val binding get() = _binding
    private lateinit var viewPager: ViewPager
    private lateinit var images: List<Int>
    private lateinit var viewPagerAdapter: PromotionAdapter
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var recommendationRecyclerView: RecyclerView
    private lateinit var shopRecyclerView: RecyclerView
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = inflater.inflate(R.layout.fragment_explore, container, false)
        
        viewPager = binding.findViewById(R.id.viewPager)
        dotsIndicator = binding.findViewById(R.id.dotsIndicator)
        
        // on below line we are initializing
        // our image list and adding data to it.
        images = ArrayList()
        images = images + R.drawable.star
        images = images + R.drawable.star_1
        images = images + R.drawable.star_2
        
        // on below line we are initializing our view
        // pager adapter and adding image list to it.
        viewPagerAdapter = PromotionAdapter(requireContext(), images)
        
        // on below line we are setting
        // adapter to our view pager.
        viewPager.adapter = viewPagerAdapter
        dotsIndicator.attachTo(viewPager)
        
        
        recommendationRecyclerView = binding.findViewById(R.id.recommendationRecyclerView)
        shopRecyclerView = binding.findViewById(R.id.shopRecyclerView)
        
        // Set up recommendation RecyclerView
        recommendationRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recommendationRecyclerView.adapter = RecommendationAdapter(generateDummyRecommendations())
        
        // Set up shop RecyclerView
        shopRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        shopRecyclerView.adapter = ShopAdapter(generateDummyShops())
        
        return binding
    }
    
    private fun generateDummyRecommendations(): List<RecommendationItem> {
        return listOf(
            RecommendationItem("1 km", "Shop A", "Location A", "4.5"),
            RecommendationItem("2 km", "Shop B", "Location B", "4.0"),
            RecommendationItem("3 km", "Shop C", "Location C", "3.5")
        )
    }
    
    private fun generateDummyShops(): List<ShopItem> {
        return listOf(
            ShopItem("10% Off"),
            ShopItem("20% Off"),
            ShopItem("30% Off")
        )
    }
    
    data class RecommendationItem(
        val distance: String,
        val shopName: String,
        val location: String,
        val ratingCount: String,
    )
    
    data class ShopItem(val discount: String)
}
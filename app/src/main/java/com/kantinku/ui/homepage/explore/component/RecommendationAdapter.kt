package com.kantinku.ui.homepage.explore.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kantinku.R
import com.kantinku.ui.homepage.explore.ExploreFragment

class RecommendationAdapter(private val items: List<ExploreFragment.RecommendationItem>) :
    RecyclerView.Adapter<RecommendationAdapter.ViewHolder>() {
    
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val distance: TextView = view.findViewById(R.id.distance)
        val shopName: TextView = view.findViewById(R.id.shopName)
        val location: TextView = view.findViewById(R.id.location)
        val ratingCount: TextView = view.findViewById(R.id.ratingCount)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recommendation_item, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.distance.text = item.distance
        holder.shopName.text = item.shopName
        holder.location.text = item.location
        holder.ratingCount.text = item.ratingCount
    }
    
    override fun getItemCount(): Int = items.size
}
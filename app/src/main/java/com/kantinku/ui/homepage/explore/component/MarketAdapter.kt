package com.kantinku.ui.homepage.explore.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kantinku.R
import com.kantinku.data.MarketData

class MarketAdapter(private val items: List<MarketData>) :
    RecyclerView.Adapter<MarketAdapter.ViewHolder>() {
    
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val shopName: TextView = view.findViewById(R.id.shopName)
        val discount: TextView = view.findViewById(R.id.rating)
        val category: TextView = view.findViewById(R.id.category)
        val canBeTaken: TextView = view.findViewById(R.id.canBeTaken)
        val ratingCount: TextView = view.findViewById(R.id.ratingCount)
        val image: ImageView = view.findViewById(R.id.roundedImageView)
        val main: View = view.findViewById(R.id.main)
        val moneyBag1: ImageView = view.findViewById(R.id.moneyBag1)
        val moneyBag2: ImageView = view.findViewById(R.id.moneyBag2)
        val moneyBag3: ImageView = view.findViewById(R.id.moneyBag3)
        val moneyBag4: ImageView = view.findViewById(R.id.moneyBag4)
    }
    
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_market, viewGroup, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.shopName.text = item.shopName
        holder.discount.text = item.discount
        holder.category.text = item.category
        holder.canBeTaken.text = item.canBeTaken
        holder.ratingCount.text = item.ratingCount.toString()
        Glide.with(holder.itemView.context.applicationContext)
            .load(item.image)
            .placeholder(R.drawable.button)
            .into(holder.image)
        
        holder.main.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }
    
    override fun getItemCount() = items.size
    
    fun setOnItemClickListener(listener: (MarketData) -> Unit) {
        onItemClickListener = listener
    }
    
    private var onItemClickListener: ((MarketData) -> Unit)? = null
}
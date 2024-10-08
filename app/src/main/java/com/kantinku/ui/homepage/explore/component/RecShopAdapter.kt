package com.kantinku.ui.homepage.explore.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kantinku.R
import com.kantinku.data.ShopData

class RecShopAdapter(private val items: List<ShopData>) :
    RecyclerView.Adapter<RecShopAdapter.ViewHolder>() {
    
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val shopName: TextView = view.findViewById(R.id.shopName)
        val discount: TextView = view.findViewById(R.id.rating)
        val ratingCount: TextView = view.findViewById(R.id.ratingCount)
        val image: ImageView = view.findViewById(R.id.roundedImageView)
        val main: View = view.findViewById(R.id.main)
        val location: TextView = view.findViewById(R.id.location)
        val distance: TextView = view.findViewById(R.id.distance)
    }
    
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_recommendation, viewGroup, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.shopName.text = item.shopName
        holder.discount.text = item.discount
        holder.ratingCount.text = item.ratingCount.toString()
        Glide.with(holder.itemView.context.applicationContext)
            .load(item.image)
            .placeholder(R.drawable.button)
            .into(holder.image)
        holder.location.text = item.location
        holder.distance.text = item.distance
        
        holder.main.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }
    
    override fun getItemCount(): Int = items.size
    
    fun setOnItemClickListener(listener: (ShopData) -> Unit) {
        onItemClickListener = listener
    }
    
    private var onItemClickListener: ((ShopData) -> Unit)? = null
}
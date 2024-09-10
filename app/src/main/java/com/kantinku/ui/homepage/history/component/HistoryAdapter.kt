package com.kantinku.ui.homepage.history.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kantinku.R


class HistoryAdapter(private val items: List<MarketData>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val shopName: TextView = view.findViewById(R.id.shopName)
        val date: TextView = view.findViewById(R.id.tvDate)
        val btnLove: ImageButton = view.findViewById(R.id.btnLove)
        val shopDesc: TextView = view.findViewById(R.id.shopDescription)
        val status: TextView = view.findViewById(R.id.tvStatus)
        val bestSeller: TextView = view.findViewById(R.id.tvBestSeller)
        val orderCount: TextView = view.findViewById(R.id.tvOrderCount)
        val btnOrder: TextView = view.findViewById(R.id.btnOrder)
        val image: ImageView = view.findViewById(R.id.ivImage)
    }
    
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_history, viewGroup, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.shopName.text = item.shopName
        Glide.with(holder.itemView.context.applicationContext)
            .load(item.image)
            .placeholder(R.drawable.button)
            .into(holder.image)
        
        
        
        holder.btnOrder.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }
    
    override fun getItemCount() = items.size
    
    fun setOnItemClickListener(listener: (MarketData) -> Unit) {
        onItemClickListener = listener
    }
    
    private var onItemClickListener: ((MarketData) -> Unit)? = null
}
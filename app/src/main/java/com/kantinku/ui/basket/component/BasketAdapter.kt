package com.kantinku.ui.basket.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kantinku.R
import com.kantinku.data.MenuData
import com.kantinku.ui.basket.BasketActivity

class BasketAdapter(private val items: List<MenuData>) :
    RecyclerView.Adapter<BasketAdapter.ViewHolder>() {
    
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val menuName: TextView = view.findViewById(R.id.menuName)
        val price: TextView = view.findViewById(R.id.menuPrice)
        val notepad: TextView = view.findViewById(R.id.notePad)
        val btnPlus: ImageButton = view.findViewById(R.id.plus)
        val quantity: TextView = view.findViewById(R.id.quantity)
        val btnMinus: ImageButton = view.findViewById(R.id.minus)
        val image: ImageView = view.findViewById(R.id.ivMenu)
    }
    
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_basket, viewGroup, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.menuName.text = item.name
        holder.price.text = item.price.toString()
        holder.quantity.text = item.quantity.toString()
        Glide.with(holder.itemView.context.applicationContext)
            .load(item.image)
            .placeholder(R.drawable.button)
            .into(holder.image)
        
        holder.btnPlus.setOnClickListener {
            BasketActivity().increment(position)
            holder.quantity.text = (item.quantity + 1).toString()
            onItemClickListener?.invoke(item)
        }
        
        holder.btnMinus.setOnClickListener {
            BasketActivity().decrement(position)
            holder.quantity.text = (item.quantity - 1).toString()
            onItemClickListener?.invoke(item)
        }
        
        holder.notepad.setOnClickListener {
        }
    }
    
    override fun getItemCount() = items.size
    
    fun setOnItemClickListener(listener: (MenuData) -> Unit) {
        onItemClickListener = listener
    }
    
    private var onItemClickListener: ((MenuData) -> Unit)? = null
}
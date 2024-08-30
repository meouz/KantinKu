package com.kantinku.ui.shop.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kantinku.R
import com.kantinku.data.MenuData
import com.kantinku.ui.shop.ShopActivity

class BestSellerAdapter(private val items: List<MenuData>) :
    RecyclerView.Adapter<BestSellerAdapter.ViewHolder>() {
    
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val menuName: TextView = view.findViewById(R.id.menuName)
        val image: ImageView = view.findViewById(R.id.ivMenu)
        val price: TextView = view.findViewById(R.id.tvPrice)
        val btnAdd: TextView = view.findViewById(R.id.tvAdd)
        val notepad: ImageView = view.findViewById(R.id.notePad)
        val btnPlus: ImageView = view.findViewById(R.id.plus)
        val quantity: TextView = view.findViewById(R.id.quantity)
        val btnMinus: ImageView = view.findViewById(R.id.minus)
    }
    
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_best_seller, viewGroup, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.menuName.text = item.name
        holder.price.text = item.price.toString()
        Glide.with(holder.itemView.context.applicationContext)
            .load(item.image)
            .placeholder(R.drawable.button)
            .into(holder.image)
        val category = item.category
        
        holder.btnAdd.setOnClickListener {
            it.visibility = View.GONE
            ShopActivity().increment(category, position)
            holder.quantity.text = (holder.quantity.text.toString().toInt() + 1).toString()
            holder.notepad.visibility = View.VISIBLE
            holder.btnMinus.visibility = View.VISIBLE
            holder.btnPlus.visibility = View.VISIBLE
            holder.quantity.visibility = View.VISIBLE
            onItemClickListener?.invoke(item)
        }
        
        holder.notepad.setOnClickListener {
        }
        
        holder.btnPlus.setOnClickListener {
            ShopActivity().increment(category, position)
            holder.quantity.text = (holder.quantity.text.toString().toInt() + 1).toString()
            onItemClickListener?.invoke(item)
        }
        
        holder.btnMinus.setOnClickListener {
            ShopActivity().decrement(category, position)
            holder.quantity.text = (holder.quantity.text.toString().toInt() - 1).toString()
            if (holder.quantity.text.toString().toInt() == 0) {
                it.visibility = View.GONE
                holder.notepad.visibility = View.GONE
                holder.btnPlus.visibility = View.GONE
                holder.quantity.visibility = View.GONE
                holder.btnAdd.visibility = View.VISIBLE
            }
            onItemClickListener?.invoke(item)
        }
    }
    
    override fun getItemCount() = items.size
    
    fun setOnItemClickListener(listener: (MenuData) -> Unit) {
        onItemClickListener = listener
    }
    
    private var onItemClickListener: ((MenuData) -> Unit)? = null
}
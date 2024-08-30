package com.kantinku.ui.homepage.order.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kantinku.R
import com.kantinku.data.MenuData

class ChildOrderAdapter(private val items: List<MenuData>) :
    RecyclerView.Adapter<ChildOrderAdapter.ViewHolder>() {
    
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val menuName: TextView = view.findViewById(R.id.tvMenuName)
        val price: TextView = view.findViewById(R.id.tvPriceCount)
        val quantity: TextView = view.findViewById(R.id.tvQuantity)
        val desc: TextView = view.findViewById(R.id.tvDescription)
        val notes: TextView = view.findViewById(R.id.tvNotes)
    }
    
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_order_child, viewGroup, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.menuName.text = item.name
        holder.quantity.text = item.quantity.toString() + "x"
        holder.desc.text = item.desc
//        holder.notes.text = item.notes
        holder.price.text = (item.price * item.quantity).toString()
        
    }
    
    override fun getItemCount() = items.size
    
    fun setOnItemClickListener(listener: (MenuData) -> Unit) {
        onItemClickListener = listener
    }
    
    private var onItemClickListener: ((MenuData) -> Unit)? = null
}
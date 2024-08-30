package com.kantinku.ui.homepage.order.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kantinku.R
import com.kantinku.data.BasketData
import com.kantinku.data.MenuData

class ParentOrderAdapter(private val items: List<MenuData>) :
    RecyclerView.Adapter<ParentOrderAdapter.ViewHolder>() {
    
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rvChild: RecyclerView = view.findViewById(R.id.rvChild)
        val tvProceed: TextView = view.findViewById(R.id.tvProceed)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val tvWait: TextView = view.findViewById(R.id.tvWait)
        val tvStatus: TextView = view.findViewById(R.id.tvWait)
    }
    
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_order_parent, viewGroup, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        var temp = 0
        items.forEach { e ->
            temp += e.quantity * e.price
        }
        holder.rvChild.adapter = ChildOrderAdapter(items)
        holder.rvChild.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.tvPrice.text = temp.toString()
    }
    
    override fun getItemCount() = items.size
    
    fun setOnItemClickListener(listener: (MenuData) -> Unit) {
        onItemClickListener = listener
    }
    
    private var onItemClickListener: ((MenuData) -> Unit)? = null
}
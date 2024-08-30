package com.kantinku.ui.homepage.order.component

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kantinku.R
import com.kantinku.data.BasketData
import com.kantinku.data.MenuData

class ParentOrderAdapter(private val items: List<BasketData>) :
    RecyclerView.Adapter<ParentOrderAdapter.ViewHolder>() {
    
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rvChild: RecyclerView = view.findViewById(R.id.rvChild)
        val tvProceed: TextView = view.findViewById(R.id.tvProceed)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val tvWait: TextView = view.findViewById(R.id.tvWait)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val tvQueue: TextView = view.findViewById(R.id.tvQueue)
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
        item.menu.forEach { e ->
            Log.d("ParentAdapter", e.toString())
            temp += e.quantity * e.price
        }
        holder.rvChild.adapter = ChildOrderAdapter(item.menu, item.notes)
        holder.rvChild.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.tvPrice.text = temp.toString()
        holder.tvProceed.text = item.proceed
        holder.tvWait.text = item.waitingTime
        holder.tvStatus.text = item.status
        holder.tvQueue.text = item.queue.toString()
    }
    
    override fun getItemCount() = items.size
    
    fun setOnItemClickListener(listener: (MenuData) -> Unit) {
        onItemClickListener = listener
    }
    
    private var onItemClickListener: ((MenuData) -> Unit)? = null
}
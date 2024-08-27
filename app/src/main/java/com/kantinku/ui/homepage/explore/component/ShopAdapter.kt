package com.kantinku.ui.homepage.explore.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kantinku.R
import com.kantinku.ui.homepage.explore.ExploreFragment

class ShopAdapter(private val dataSet: List<ExploreFragment.ShopItem>) :
    RecyclerView.Adapter<ShopAdapter.ViewHolder>() {
    
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Define click listener for the ViewHolder's View
        val discount: TextView = view.findViewById(R.id.discount)
    }
    
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.shop_item_adapter, viewGroup, false)
        
        return ViewHolder(view)
    }
    
    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val item = dataSet[position]
        viewHolder.discount.text = item.discount
    }
    
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
    
}
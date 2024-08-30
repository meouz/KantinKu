package com.kantinku.ui.homepage.explore.component

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.kantinku.R
import java.util.Objects

class PromotionAdapter(val context: Context, val images: List<Int>) : PagerAdapter() {
    override fun getCount(): Int {
        return images.size
    }
    
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }
    
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView: View = mLayoutInflater.inflate(R.layout.item_promotion, container, false)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        imageView.setImageResource(images[position])
        Objects.requireNonNull(container).addView(itemView)
        
        return itemView
    }
    
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}
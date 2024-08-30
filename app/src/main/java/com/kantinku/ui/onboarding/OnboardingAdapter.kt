package com.kantinku.ui.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.kantinku.R
import java.util.Objects

class OnboardingAdapter(
    val context: Context,
    val images: List<Int>,
    val text: List<String>,
    val desc: List<String>,
) : PagerAdapter() {
    override fun getCount(): Int {
        return images.size
    }
    
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }
    
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView: View = mLayoutInflater.inflate(R.layout.item_onboarding, container, false)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView1: TextView = itemView.findViewById(R.id.textView1)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
        
        imageView.setImageResource(images[position])
        textView1.text = text[position]
        textView2.text = desc[position]
        Objects.requireNonNull(container).addView(itemView)
        
        return itemView
    }
    
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}
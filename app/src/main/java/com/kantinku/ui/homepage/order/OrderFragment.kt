package com.kantinku.ui.homepage.order

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.kantinku.databinding.FragmentOrderBinding
import com.kantinku.ui.homepage.order.component.ParentOrderAdapter
import com.kantinku.ui.profile.ProfileActivity

class OrderFragment : Fragment() {
    private lateinit var _binding: FragmentOrderBinding
    private val binding get() = _binding
    private lateinit var rvOrder: RecyclerView
    private var auth = FirebaseAuth.getInstance()
    private lateinit var viewModel: OrderViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        viewModel = OrderViewModel()
        
        binding.profile.setOnClickListener {
            Intent(requireContext(), ProfileActivity::class.java).also {
                startActivity(it)
            }
        }
        
        val userId = auth.currentUser?.uid.toString()
        
        rvOrder = binding.rvOrder
        rvOrder.adapter = ParentOrderAdapter(viewModel.getBasket())
        rvOrder.layoutManager = LinearLayoutManager(requireContext())
        
        viewModel.postData(userId) {
            if (viewModel.getBasket().isEmpty()) {
                binding.rvOrder.visibility = View.GONE
                binding.tvCheck.visibility = View.VISIBLE
                binding.ivCheck.visibility = View.VISIBLE
            } else {
                binding.rvOrder.visibility = View.VISIBLE
                binding.tvCheck.visibility = View.GONE
                binding.ivCheck.visibility = View.GONE
            }
            rvOrder.adapter?.notifyDataSetChanged()
        }
        
        (rvOrder.adapter as ParentOrderAdapter).setOnItemClickListener {
            viewModel.updateStatus(it)
            rvOrder.adapter?.notifyDataSetChanged()
        }
        
        return binding.root
    }
}
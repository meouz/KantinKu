package com.kantinku.ui.homepage.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.kantinku.databinding.FragmentHistoryBinding
import com.kantinku.ui.homepage.history.component.HistoryAdapter
import com.kantinku.ui.homepage.order.component.ParentOrderAdapter
import com.kantinku.ui.profile.ProfileActivity

class HistoryFragment : Fragment() {
    private lateinit var _binding: FragmentHistoryBinding
    private val binding get() = _binding
    private lateinit var viewModel: HistoryViewModel
    private lateinit var rvHistory: RecyclerView
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        
        binding.profile.setOnClickListener {
            Intent(requireContext(), ProfileActivity::class.java).also {
                startActivity(it)
            }
        }
        
        rvHistory = binding.rvHistory
        rvHistory.adapter = ParentOrderAdapter(viewModel.getData().value)
        rvHistory.layoutManager = LinearLayoutManager(requireContext())
        
        viewModel.setData {
            rvHistory.adapter?.notifyDataSetChanged()
        }
        
        return binding.root
    }
}
package com.kantinku.ui.homepage.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kantinku.databinding.FragmentHistoryBinding
import com.kantinku.ui.profile.ProfileActivity

class HistoryFragment : Fragment() {
    private lateinit var _binding: FragmentHistoryBinding
    private val binding get() = _binding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        
        binding.profile.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java).also {
                startActivity(it)
            }
        }
        
        return binding.root
    }
}
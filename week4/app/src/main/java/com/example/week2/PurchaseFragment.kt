package com.example.week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week2.databinding.FragmentPurchaseBinding
import com.google.android.material.tabs.TabLayoutMediator

class PurchaseFragment : Fragment() {
    private var _binding: FragmentPurchaseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPurchaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = PurchasePagerAdapter(this)
        binding.viewPagerPurchase.adapter = adapter

        TabLayoutMediator(binding.tabLayoutPurchase, binding.viewPagerPurchase) { tab, position ->
            tab.text = when (position) {
                0 -> "All"
                1 -> "Top & T-shirts"
                2 -> "Sale"
                else -> null
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

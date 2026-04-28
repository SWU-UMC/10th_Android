package com.example.nike.ui.shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.nike.R
import com.example.nike.databinding.FragmentShopBinding
import com.google.android.material.tabs.TabLayoutMediator

class ShopFragment : Fragment(R.layout.fragment_shop) {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShopBinding.bind(view)

        binding.viewPager.adapter = ShopPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Top & T-shirts"
                1 -> "Sale"
                else -> ""
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

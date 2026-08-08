package com.example.week2

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PurchasePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllProductsFragment()
            1 -> TopsTshirtsFragment()
            2 -> SaleFragment()
            else -> AllProductsFragment()
        }
    }
}

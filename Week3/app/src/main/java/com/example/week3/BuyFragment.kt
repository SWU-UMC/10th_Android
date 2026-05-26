package com.example.week3

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.week3.databinding.FragmentBuyBinding
import com.google.android.material.tabs.TabLayout

class BuyFragment : Fragment(R.layout.fragment_buy) {

    private var _binding: FragmentBuyBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBuyBinding.bind(view)

        setupTabs()
    }

    private fun setupTabs() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.all)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.Shirts)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.Shoes)))

        replaceTabFragment(BuyAllFragment())

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                updateTabStyle(tab, true)

                when (tab?.position) {
                    0 -> replaceTabFragment(BuyAllFragment())
                    1 -> replaceTabFragment(BuyTopsFragment())
                    2 -> replaceTabFragment(BuyShoesFragment())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                updateTabStyle(tab, false)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        updateTabStyle(binding.tabLayout.getTabAt(0), true)
    }

    private fun replaceTabFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.tabContent, fragment)
            .commit()
    }

    private fun updateTabStyle(tab: TabLayout.Tab?, isBold: Boolean) {
        tab?.let { currentTab ->
            val rawText = currentTab.text?.toString() ?: ""

            val spannableString = SpannableString(rawText)

            if (isBold) {
                spannableString.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    rawText.length,
                    SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } else {
                spannableString.setSpan(
                    StyleSpan(Typeface.NORMAL),
                    0,
                    rawText.length,
                    SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            currentTab.text = spannableString
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
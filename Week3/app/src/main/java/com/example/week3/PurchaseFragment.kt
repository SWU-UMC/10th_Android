package com.example.week3

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PurchaseFragment : Fragment(R.layout.fragment_purchase) {

    private lateinit var dataStoreManager: DataStoreManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataStoreManager = DataStoreManager(requireContext())


        val tabLayout = view.findViewById<TabLayout>(R.id.purchase_tab_layout)


        if (tabLayout.tabCount < 4) {
            tabLayout.addTab(tabLayout.newTab().setText("Sale"))
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                when (tab?.position) {
                    0 -> { /* 전체 */ }
                    1 -> { /* Tops & T-Shirts */ }
                    2 -> { /* Shoes */ }
                    3 -> { /* Sale */ }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


        val heartBtn = view.findViewById<ImageView>(R.id.iv_socks)

        heartBtn.setOnClickListener {
            val currentProduct = Product("Nike Everyday Plus", "$10", R.drawable.socks)
            toggleWishlist(currentProduct)
        }
    }

    private fun toggleWishlist(product: Product) {
        viewLifecycleOwner.lifecycleScope.launch {
            // 1. 현재 위시리스트 가져오기
            val currentWishlist = dataStoreManager.getWishlist().first().toMutableList()

            // 2. 이미 있으면 제거, 없으면 추가
            val isExist = currentWishlist.any { it.name == product.name }
            if (isExist) {
                currentWishlist.removeAll { it.name == product.name }
                Toast.makeText(context, "위시리스트에서 제거되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                currentWishlist.add(product)
                Toast.makeText(context, "위시리스트에 추가되었습니다!", Toast.LENGTH_SHORT).show()
            }

            dataStoreManager.saveWishlist(currentWishlist)
        }
    }
}
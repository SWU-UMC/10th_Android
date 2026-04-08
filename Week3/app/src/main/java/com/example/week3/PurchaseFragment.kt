package com.example.week3

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PurchaseFragment : Fragment(R.layout.fragment_purchase) {

    private lateinit var dataStoreManager: DataStoreManager
    private val products = listOf(
        Product("Nike Everyday Plus Cushioned", "$10", R.drawable.socks),
        Product("Nike Everyday Plus Cushioned2", "$10", R.drawable.socks),
        Product("Nike Everyday Plus Cushioned3", "$10", R.drawable.socks),
        Product("Nike Everyday Plus Cushioned4", "$10", R.drawable.socks)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataStoreManager = DataStoreManager(requireContext())

        val tabLayout = view.findViewById<TabLayout>(R.id.purchase_tab_layout)
        val scrollView = view.findViewById<ScrollView>(R.id.purchase_scroll_view)


        val heartButtons = listOf<ImageView>(
            view.findViewById(R.id.btn_heart1),
            view.findViewById(R.id.btn_heart2),
            view.findViewById(R.id.btn_heart3),
            view.findViewById(R.id.btn_heart4)
        )


        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.getWishlist().collect { wishlist ->
                heartButtons.forEachIndexed { index, imageView ->
                    val isWishlisted = wishlist.any { it.name == products[index].name }
                    if (isWishlisted) {
                        imageView.setImageResource(R.drawable.ic_heart_filled)
                    } else {
                        imageView.setImageResource(R.drawable.ic_heart_empty)
                    }
                }
            }
        }


        heartButtons.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                toggleWishlist(products[index])
            }
        }

        if (tabLayout.tabCount < 4) tabLayout.addTab(tabLayout.newTab().setText("Sale"))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                scrollView.visibility = if (tab?.position == 0) View.VISIBLE else View.GONE
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun toggleWishlist(product: Product) {
        viewLifecycleOwner.lifecycleScope.launch {
            val currentWishlist = dataStoreManager.getWishlist().first().toMutableList()
            val isExist = currentWishlist.any { it.name == product.name }

            if (isExist) {
                currentWishlist.removeAll { it.name == product.name }
                Toast.makeText(context, "위시리스트 제거", Toast.LENGTH_SHORT).show()
            } else {
                currentWishlist.add(product)
                Toast.makeText(context, "위시리스트 추가!", Toast.LENGTH_SHORT).show()
            }
            dataStoreManager.saveWishlist(currentWishlist)
        }
    }
}
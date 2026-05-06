package com.example.week3

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchaseFragment : Fragment(R.layout.fragment_purchase) {

    private val viewModel: ShoppingViewModel by viewModels()

    private val products = listOf(
        Product("Nike Everyday Plus Cushioned", "$10", R.drawable.socks),
        Product("Nike Everyday Plus Cushioned2", "$10", R.drawable.socks),
        Product("Nike Everyday Plus Cushioned3", "$10", R.drawable.socks),
        Product("Nike Everyday Plus Cushioned4", "$10", R.drawable.socks)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.purchase_tab_layout)
        val scrollView = view.findViewById<ScrollView>(R.id.purchase_scroll_view)

        val heartButtons = listOf<ImageView>(
            view.findViewById(R.id.btn_heart1),
            view.findViewById(R.id.btn_heart2),
            view.findViewById(R.id.btn_heart3),
            view.findViewById(R.id.btn_heart4)
        )

        viewModel.wishlist.observe(viewLifecycleOwner, Observer { wishlist ->
            heartButtons.forEachIndexed { index, imageView ->
                val isWishlisted = wishlist.any { it.name == products[index].name }

                if (isWishlisted) {
                    imageView.setImageResource(R.drawable.ic_heart_filled)
                } else {
                    imageView.setImageResource(R.drawable.ic_heart_empty)
                }
            }
        })


        heartButtons.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                viewModel.toggleWishlist(products[index])

                val isNowWishlisted = viewModel.wishlist.value?.any {
                    it.name == products[index].name
                } ?: false

                Toast.makeText(
                    context,
                    if (isNowWishlisted) "위시리스트 제거" else "위시리스트 추가!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        if (tabLayout.tabCount < 4) {
            tabLayout.addTab(tabLayout.newTab().setText("Sale"))
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                scrollView.visibility =
                    if (tab?.position == 0) View.VISIBLE else View.GONE
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}
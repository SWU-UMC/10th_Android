package com.example.week3 // 패키지명을 com.example.week3로 통일

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
class WishlistFragment : Fragment(R.layout.fragment_wishlist), ProductClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val wishList = listOf(
            Product("Air Jordan 1 Mid", "US$125", R.drawable.jordan1),
            Product("Nike Everyday Plus", "US$10", R.drawable.socks)
        )


        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_wishlist_products)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)


        recyclerView.adapter = ProductAdapter(wishList, this)
    }


    override fun onProductClick(productName: String, price: String) {
        val purchaseFragment = PurchaseFragment()

        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, purchaseFragment)
            .addToBackStack(null)
            .commit()
    }
}
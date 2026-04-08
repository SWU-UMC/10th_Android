package com.example.week3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class WishlistFragment : Fragment(R.layout.fragment_wishlist), ProductClickListener {

    private lateinit var dataStoreManager: DataStoreManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        dataStoreManager = DataStoreManager(requireContext())


        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_wishlist_products)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)


        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.getWishlist().collect { savedWishlist ->
                recyclerView.adapter = ProductAdapter(savedWishlist, this@WishlistFragment)
            }
        }
    }

    override fun onProductClick(productName: String, price: String) {
        val purchaseFragment = PurchaseFragment()

        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, purchaseFragment)
            .addToBackStack(null)
            .commit()
    }
}
package com.example.week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week2.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Dummy wishlist data
        val dummyWishlist = mutableListOf(
            Product(1, "Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)", "US$16", R.drawable.training_ankle_socks, true),
            Product(2, "Air Jordan XXXVI", "Basketball Shoes", "US$185", R.drawable.air_jordan_xxxvi, true)
        )

        val adapter = ProductAdapter(
            dummyWishlist,
            onItemClick = { product ->
                Toast.makeText(context, "${product.name} clicked", Toast.LENGTH_SHORT).show()
            },
            onWishlistClick = { product, position ->
                product.isWishlisted = !product.isWishlisted
                // In wishlist fragment, usually removing from wishlist means removing from the list
                if (!product.isWishlisted) {
                    dummyWishlist.removeAt(position)
                    binding.rvWishlistProducts.adapter?.notifyItemRemoved(position)
                } else {
                    binding.rvWishlistProducts.adapter?.notifyItemChanged(position)
                }
            }
        )

        binding.rvWishlistProducts.layoutManager = GridLayoutManager(context, 2)
        binding.rvWishlistProducts.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

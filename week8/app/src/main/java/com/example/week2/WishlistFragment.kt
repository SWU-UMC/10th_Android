package com.example.week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.fragment.app.Fragment
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
        setupComposeView()
    }

    private fun setupComposeView() {
        // Dummy wishlist data
        val dummyWishlist = listOf(
            Product(1, "Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)", "US$16", R.drawable.img_training_ankle_socks, true),
            Product(2, "Air Jordan XXXVI", "Basketball Shoes", "US$185", R.drawable.img_air_jordan_xxxvi, true)
        )

        binding.cvWishlistProducts.setContent {
            val wishlistItems = remember { mutableStateListOf<Product>().apply { addAll(dummyWishlist) } }

            ProductGrid(
                products = wishlistItems,
                onItemClick = { product ->
                    Toast.makeText(context, "${product.name} clicked", Toast.LENGTH_SHORT).show()
                },
                onWishlistClick = { product ->
                    val index = wishlistItems.indexOfFirst { it.id == product.id }
                    if (index != -1) {
                        val updatedProduct = wishlistItems[index].copy(isWishlisted = !wishlistItems[index].isWishlisted)
                        if (!updatedProduct.isWishlisted) {
                            wishlistItems.removeAt(index)
                        } else {
                            wishlistItems[index] = updatedProduct
                        }
                    }
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

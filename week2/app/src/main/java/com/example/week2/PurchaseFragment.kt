package com.example.week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week2.databinding.FragmentPurchaseBinding

class PurchaseFragment : Fragment() {
    private var _binding: FragmentPurchaseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPurchaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val dummyProducts = listOf(
            Product(1, "Nike Everyday Plus Cushioned", "Training Crew Socks (6 Pairs)", "US$10", R.drawable.img_nike_everyday_plus_cushioned, category = "Training Crew Socks"),
            Product(2, "Nike Elite Crew", "Basketball Socks", "US$16", R.drawable.img_training_ankle_socks, category = "Basketball Socks"),
            Product(3, "Nike Air Force 1 '07", "Women's Shoes", "US$115", R.drawable.img_nike_air_force, category = "Women's Shoes"),
            Product(4, "Jordan Nike Air Force 1 '07 Essentials", "Men's Shoes", "US$115", R.drawable.img_air_jordan_xxxvi, category = "Men's Shoes")
        )

        val adapter = ProductAdapter(
            dummyProducts,
            onItemClick = { product ->
                navigateToDetail(product)
            },
            onWishlistClick = { product, position ->
                product.isWishlisted = !product.isWishlisted
                binding.rvPurchaseProducts.adapter?.notifyItemChanged(position)
            }
        )

        binding.rvPurchaseProducts.layoutManager = GridLayoutManager(context, 2)
        binding.rvPurchaseProducts.adapter = adapter
    }

    private fun navigateToDetail(product: Product) {
        // Navigation Component를 사용하여 상세 화면으로 이동
        val bundle = Bundle().apply {
            putParcelable("product", product)
        }
        findNavController().navigate(R.id.action_purchaseFragment_to_productDetailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

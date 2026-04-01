package com.example.week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val dummyProducts = listOf(
            Product(1, "Air Jordan XXXVI", "Basketball Shoes", "US$185", R.drawable.air_jordan_xxxvi),
            Product(2, "Nike Air Force 1 '07", "Men's Shoes", "US$115", R.drawable.nike_air_force),
            Product(3, "Nike Everyday Plus Cushioned", "Training Socks", "US$20", R.drawable.nike_everyday_plus_cushioned)
        )

        val adapter = ProductAdapter(
            dummyProducts,
            onItemClick = { product ->
                navigateToDetail(product)
            },
            onWishlistClick = { product, position ->
                product.isWishlisted = !product.isWishlisted
                binding.rvHomeProducts.adapter?.notifyItemChanged(position)
            }
        )

        binding.rvHomeProducts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvHomeProducts.adapter = adapter
    }

    private fun navigateToDetail(product: Product) {
        val detailFragment = ProductDetailFragment.newInstance(product)
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_frame, detailFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

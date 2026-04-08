package com.example.nike.ui.theme

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.R
import com.example.nike.data.model.ProductDummyData
import com.example.nike.databinding.FragmentHomeBinding
import com.example.nike.ui.theme.HomeProductAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeProductAdapter: HomeProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        val latestProducts = ProductDummyData.getProducts().filter { it.isNew }

        homeProductAdapter = HomeProductAdapter(latestProducts) { product ->
            val intent = Intent(requireContext(), ProductDetailFragment::class.java)
            intent.putExtra("product_id", product.id)
            startActivity(intent)
        }

        binding.rvLatestProducts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvLatestProducts.adapter = homeProductAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
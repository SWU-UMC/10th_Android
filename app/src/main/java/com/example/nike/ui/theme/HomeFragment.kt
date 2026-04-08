package com.example.nike.ui.theme

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.R
import com.example.nike.data.model.ProductDummyData
import com.example.nike.data.repository.ProductUiModel
import com.example.nike.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeProductAdapter: HomeProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        val latestProducts = ProductDummyData.getProducts()
            .filter { it.isNew }
            .map { product ->
                ProductUiModel(
                    id = product.id,
                    name = product.name,
                    price = product.price,
                    imageResId = product.imageResId,
                    category = product.category,
                    isNew = product.isNew,
                    isLiked = product.isLiked
                )
            }

        homeProductAdapter = HomeProductAdapter(
            onHeartClick = { product ->
                // 홈 화면에서 하트 기능 안 쓰면 일단 비워둬도 됨
                // 나중에 viewModel.toggleWishlist(product.id) 연결하면 됨
            },
            onItemClick = { product ->
                val intent = Intent(requireContext(), ProductDetailActivity::class.java)
                intent.putExtra("product_id", product.id)
                startActivity(intent)
            }
        )

        binding.rvLatestProducts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvLatestProducts.adapter = homeProductAdapter
        homeProductAdapter.submitList(latestProducts)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
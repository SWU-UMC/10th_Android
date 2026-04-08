package com.example.nike.ui.theme

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.R
import com.example.nike.databinding.FragmentWishListBinding

class WishlistFragment : Fragment(R.layout.fragment_wish_list) {

    private var _binding: FragmentWishListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by activityViewModels()
    private lateinit var adapter: ProductGridAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWishListBinding.bind(view)

        adapter = ProductGridAdapter(
            productList = mutableListOf(),
            onItemClick = { product ->
                val intent = Intent(requireContext(), ProductDetailActivity::class.java)
                intent.putExtra("product_id", product.id)
                startActivity(intent)
            }
        )

        binding.rvWishlist.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvWishlist.adapter = adapter

        viewModel.allProducts.observe(viewLifecycleOwner) { products ->
            adapter.updateList(products.filter { it.isLiked })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
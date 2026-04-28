package com.example.nike.ui.wishlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nike.R
import com.example.nike.databinding.FragmentWishListBinding
import com.example.nike.ui.common.ProductGridAdapter
import com.example.nike.ui.product.ProductNavigator
import com.example.nike.ui.product.ProductViewModel

class WishlistFragment : Fragment(R.layout.fragment_wish_list) {

    private var _binding: FragmentWishListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by activityViewModels()
    private lateinit var adapter: ProductGridAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWishListBinding.bind(view)

        adapter = ProductGridAdapter(
            onItemClick = { product -> (activity as? ProductNavigator)?.openProductDetail(product.id) },
            onHeartClick = { product -> viewModel.toggleWishlist(product.id) }
        )

        binding.rvWishlist.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvWishlist.adapter = adapter

        viewModel.allProducts.observe(viewLifecycleOwner) { products ->
            adapter.submitList(products.filter { it.isLiked })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

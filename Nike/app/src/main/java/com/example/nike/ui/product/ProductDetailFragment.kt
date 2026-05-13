package com.example.nike.ui.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.nike.R
import com.example.nike.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by activityViewModels()
    private var productId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productId = requireArguments().getInt(ARG_PRODUCT_ID)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductDetailBinding.bind(view)

        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.btnAddCart.setOnClickListener {
            viewModel.addToCart(productId)
        }

        binding.btnWishlist.setOnClickListener {
            viewModel.toggleWishlist(productId)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allProducts.collect { products ->
                    products.firstOrNull { it.id == productId }?.let { product ->
                        binding.detailImage.setImageResource(product.imageResId)
                        binding.detailTitle.text = product.name
                        binding.detailCategory.text = product.description
                        binding.detailName.text = product.name
                        binding.detailPrice.text = "US$${product.price}"
                        binding.detailDescription.text = product.description
                        binding.btnWishlist.text = if (product.isLiked) {
                            "Remove from Wishlist"
                        } else {
                            "Add to Wishlist"
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "ProductDetailFragment"
        private const val ARG_PRODUCT_ID = "product_id"

        fun newInstance(productId: Int): ProductDetailFragment {
            return ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PRODUCT_ID, productId)
                }
            }
        }
    }
}

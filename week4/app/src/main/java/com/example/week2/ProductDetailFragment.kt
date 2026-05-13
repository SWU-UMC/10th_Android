package com.example.week2

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.week2.databinding.FragmentProductDetailBinding
import kotlinx.coroutines.launch

class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataStoreManager = DataStoreManager(requireContext())

        val product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("product", Product::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable<Product>("product")
        }

        product?.let { currentProduct ->
            binding.tvHeaderTitle.text = currentProduct.name
            binding.ivDetailImage.setImageResource(currentProduct.imageResId)
            binding.tvDetailCategory.text = currentProduct.category
            binding.tvDetailName.text = currentProduct.name
            binding.tvDetailPrice.text = currentProduct.price
            
            // 실시간 반영을 위해 DataStore에서 상태 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    dataStoreManager.productsFlow.collect { products ->
                        val updatedProduct = products.find { it.id == currentProduct.id }
                        updatedProduct?.let {
                            updateWishlistButton(it.isWishlisted)
                        }
                    }
                }
            }
        }

        binding.ibBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnWishlist.setOnClickListener {
            product?.let { currentProduct ->
                viewLifecycleOwner.lifecycleScope.launch {
                    val isCurrentlyWishlisted = binding.ivWishlistHeart.isSelected
                    dataStoreManager.updateWishlistStatus(currentProduct.id, !isCurrentlyWishlisted)
                }
            }
        }
    }

    private fun updateWishlistButton(isWishlisted: Boolean) {
        binding.ivWishlistHeart.isSelected = isWishlisted
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

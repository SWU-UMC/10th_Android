package com.example.week2

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.week2.databinding.FragmentProductDetailBinding
import com.example.week2.viewmodel.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val initialProduct = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("product", Product::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable<Product>("product")
        }

        initialProduct?.let { 
            viewModel.setProductId(it.id)
            // 초기 UI 설정 (로딩 전 빈 화면 방지)
            setupInitialUI(it)
        }

        observeViewModel()

        binding.ibBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnWishlist.setOnClickListener {
            viewModel.toggleWishlist()
        }
    }

    private fun setupInitialUI(product: Product) {
        binding.tvHeaderTitle.text = product.name
        binding.ivDetailImage.setImageResource(product.imageResId)
        binding.tvDetailCategory.text = product.category
        binding.tvDetailName.text = product.name
        binding.tvDetailPrice.text = product.price
        updateWishlistButton(product.isWishlisted)
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.product.collect { product ->
                    product?.let {
                        binding.tvHeaderTitle.text = it.name
                        binding.tvDetailName.text = it.name
                        updateWishlistButton(it.isWishlisted)
                    }
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

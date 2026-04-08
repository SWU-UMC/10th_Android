package com.example.week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.week2.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = arguments?.getParcelable<Product>("product")

        product?.let {
            binding.tvHeaderTitle.text = it.name
            binding.ivDetailImage.setImageResource(it.imageResId)
            binding.tvDetailCategory.text = it.category
            binding.tvDetailName.text = it.name
            binding.tvDetailPrice.text = it.price
            
            updateWishlistButton(it.isWishlisted)
        }

        binding.ibBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnWishlist.setOnClickListener {
            product?.let {
                it.isWishlisted = !it.isWishlisted
                updateWishlistButton(it.isWishlisted)
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

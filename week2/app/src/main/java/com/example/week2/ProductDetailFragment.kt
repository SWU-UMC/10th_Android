package com.example.week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        val product = arguments?.getSerializable("product") as? Product

        product?.let {
            binding.tvHeaderTitle.text = it.name
            binding.ivDetailImage.setImageResource(it.imageResId)
            binding.tvDetailCategory.text = it.description // Using description as category for now
            binding.tvDetailName.text = it.name
            binding.tvDetailPrice.text = it.price
            
            updateWishlistButton(it.isWishlisted)
        }

        binding.ibBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.btnWishlist.setOnClickListener {
            product?.let {
                it.isWishlisted = !it.isWishlisted
                updateWishlistButton(it.isWishlisted)
            }
        }
    }

    private fun updateWishlistButton(isWishlisted: Boolean) {
        if (isWishlisted) {
            binding.btnWishlist.text = "위시리스트 ❤️"
        } else {
            binding.btnWishlist.text = "위시리스트 ♡"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(product: Product): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            val args = Bundle()
            args.putSerializable("product", product)
            fragment.arguments = args
            return fragment
        }
    }
}

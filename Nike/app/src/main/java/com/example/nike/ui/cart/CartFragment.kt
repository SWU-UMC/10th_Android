package com.example.nike.ui.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.nike.R
import com.example.nike.databinding.FragmentCartBinding

class CartFragment : Fragment(R.layout.fragment_cart) {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCartBinding.bind(view)

        binding.btnOrder.setOnClickListener {
            (activity as? CartNavigator)?.goToShop()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

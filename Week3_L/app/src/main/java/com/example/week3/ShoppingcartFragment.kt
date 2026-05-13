package com.example.week3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.navigation.NavOptions

class ShoppingcartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        return inflater.inflate(R.layout.fragment_shoppingcart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.buy_button)

        button.setOnClickListener {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.btnShoppingcart, true)
                .build()

            findNavController().navigate(R.id.btnBuy, null, navOptions)
        }

    }
}
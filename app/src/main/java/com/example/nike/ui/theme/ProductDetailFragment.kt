package com.example.nike.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.nike.R

class ProductDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)

        val detailImage = view.findViewById<ImageView>(R.id.detailImage)
        val detailName = view.findViewById<TextView>(R.id.detailName)
        val detailPrice = view.findViewById<TextView>(R.id.detailPrice)

        val name = arguments?.getString("name") ?: ""
        val price = arguments?.getString("price") ?: ""
        val imageResId = arguments?.getInt("image") ?: R.drawable.home_banner

        detailImage.setImageResource(imageResId)
        detailName.text = name
        detailPrice.text = price

        return view
    }
}
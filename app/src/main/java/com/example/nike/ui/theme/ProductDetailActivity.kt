package com.example.nike.ui.theme

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nike.R
import com.example.nike.data.model.ProductDummyData

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_product_detail)

        val detailImage = findViewById<ImageView>(R.id.detailImage)
        val detailName = findViewById<TextView>(R.id.detailName)
        val detailPrice = findViewById<TextView>(R.id.detailPrice)

        val productId = intent.getIntExtra("product_id", -1)
        val product = ProductDummyData.getProducts().find { it.id == productId }

        detailImage.setImageResource(product?.imageResId ?: R.drawable.home_banner)
        detailName.text = product?.name ?: getString(R.string.app_name)
        detailPrice.text = product?.let { "${it.price}원" }.orEmpty()
    }
}

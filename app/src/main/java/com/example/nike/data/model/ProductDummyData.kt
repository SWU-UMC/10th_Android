package com.example.nike.data.model

import com.example.nike.R

object ProductDummyData {

    fun getProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                name = "Nike Air Max",
                price = 129000,
                imageResId = R.drawable.nike_air_force,
                category = "TOP",
                isNew = true
            ),
            Product(
                id = 2,
                name = "Nike Hoodie",
                price = 99000,
                imageResId = R.drawable.nike_air_force,
                category = "TOP",
                isNew = true
            ),
            Product(
                id = 3,
                name = "Nike T-shirt",
                price = 59000,
                imageResId = R.drawable.nike_air_force,
                category = "TOP",
                isNew = false
            ),
            Product(
                id = 4,
                name = "Nike Sale Shoes",
                price = 79000,
                imageResId = R.drawable.nike_air_force,
                category = "SALE",
                isNew = false
            ),
            Product(
                id = 5,
                name = "Nike Sale Cap",
                price = 35000,
                imageResId = R.drawable.nike_air_force,
                category = "SALE",
                isNew = false
            ),
            Product(
                id = 6,
                name = "Nike Windrunner",
                price = 149000,
                imageResId = R.drawable.nike_air_force,
                category = "TOP",
                isNew = true
            )
        )
    }
}
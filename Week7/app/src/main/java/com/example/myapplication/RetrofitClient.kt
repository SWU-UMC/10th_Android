package com.example.myapplication

import com.example.myapplication.model.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


val sampleStoreProducts = listOf(
    Product(1, "Adidas", R.drawable.adidas, "$89.00", "Tops & Shirts"),
    Product(2, "Nike", R.drawable.nike, "$99.00", "Tops & Shirts"),
    Product(3, "Lecoqsportif", R.drawable.lecoqsportif, "$120.00", "Sale"),
    Product(4, "Fila", R.drawable.fila, "$59.00", "Sale")
)


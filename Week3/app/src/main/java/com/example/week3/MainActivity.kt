package com.example.week3

import CartFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.week2final.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dataStoreManager = DataStoreManager(this)


        lifecycleScope.launch {
            val existingData = dataStoreManager.getAllProducts().first()
            if (existingData.isEmpty()) {
                val dummyData = listOf(
                    Product("AirJordan1", "$115", R.drawable.jordan1),
                    Product("AirJordan2", "$120", R.drawable.jordan2),
                    Product("Nike Everyday Plus", "$10", R.drawable.socks)
                )
                dataStoreManager.saveAllProducts(dummyData)
            }
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.itemIconTintList = null


        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }


        bottomNav.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.menu_home -> HomeFragment()
                R.id.menu_purchase -> PurchaseFragment()
                R.id.menu_wishlist -> WishlistFragment()
                R.id.menu_cart -> CartFragment()
                R.id.menu_profile -> ProfileFragment()
                else -> HomeFragment()
            }
            loadFragment(fragment)
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.commit()
    }
}
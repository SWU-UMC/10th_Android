package com.example.week2final


import CartFragment
import HomeFragment
import ProfileFragment
import PurchaseFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.google.android.material.bottomnavigation.BottomNavigationView
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.itemIconTintList = null

        loadFragment(HomeFragment())

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.menu_purchase -> {
                    loadFragment(PurchaseFragment())
                    true
                }
                R.id.menu_wishlist -> {
                    loadFragment(WishlistFragment())
                    true
                }
                R.id.menu_cart -> {
                    loadFragment(CartFragment())
                    true
                }
                R.id.menu_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.commit()
    }
}
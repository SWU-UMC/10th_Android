package com.example.week3 // 패키지명을 week3로 통일

import CartFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.week2final.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.itemIconTintList = null

        loadFragment(HomeFragment())

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
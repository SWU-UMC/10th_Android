package com.example.week3 // 패키지명을 week3로 통일

import CartFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.week2final.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

// 만약 Fragment 파일들이 다른 폴더에 있다면 아래처럼 명시적으로 임포트가 필요합니다.
// 하지만 보통 같은 패키지(com.example.week3) 안에 있다면 아래 import 문들은 없어도 됩니다.

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.itemIconTintList = null

        // 앱 실행 시 첫 화면 설정
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
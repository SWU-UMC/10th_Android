package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 1. 기본 시스템 바 설정 (기존 코드 유지)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2. 하단 바와 프래그먼트 연결 로직 추가
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // 앱 처음 실행 시 홈 화면 표시
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // 아이콘 클릭 리스너
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.HouseSimple -> replaceFragment(HomeFragment())
                R.id.ListMagnifyingGlass -> replaceFragment(BuyFragment())
                R.id.HeartStraight -> replaceFragment(WishlistFragment())
                R.id.Bagsimple -> replaceFragment(CartFragment())
                R.id.User -> replaceFragment(ProfileFragment())
            }
            true
        }
    }

    // 화면 교체 함수
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }
}
package com.example.nike

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nike.databinding.ActivityMainBinding
import com.example.nike.ui.theme.CartFragment
import com.example.nike.ui.theme.HomeFragment
import com.example.nike.ui.theme.ProfileFragment
import com.example.nike.ui.theme.ShopFragment
import com.example.nike.ui.theme.WishlistFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "LIFE_QUIZ"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate")

        // 앱 처음 실행 시 홈 화면 표시
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // 하단 네비게이션 클릭 이벤트
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> replaceFragment(HomeFragment())
                R.id.menu_shop -> replaceFragment(ShopFragment())
                R.id.menu_wishlist -> replaceFragment(WishlistFragment())
                R.id.menu_cart -> replaceFragment(CartFragment())
                R.id.menu_profile -> replaceFragment(ProfileFragment())
            }
            true
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
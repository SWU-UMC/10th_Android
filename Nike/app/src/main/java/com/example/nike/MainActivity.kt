package com.example.nike

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nike.databinding.ActivityMainBinding
import com.example.nike.ui.cart.CartFragment
import com.example.nike.ui.cart.CartNavigator
import com.example.nike.ui.home.HomeFragment
import com.example.nike.ui.product.ProductDetailFragment
import com.example.nike.ui.product.ProductNavigator
import com.example.nike.ui.profile.ProfileFragment
import com.example.nike.ui.shop.ShopFragment
import com.example.nike.ui.wishlist.WishlistFragment

class MainActivity : AppCompatActivity(), CartNavigator, ProductNavigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate")

        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.menu_home
            replaceFragment(HomeFragment())
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> replaceFragment(HomeFragment())
                R.id.menu_shop -> replaceFragment(ShopFragment())
                R.id.menu_wishlist -> replaceFragment(WishlistFragment())
                R.id.menu_cart -> replaceFragment(CartFragment())
                R.id.menu_profile -> replaceFragment(ProfileFragment())
                else -> false
            }
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

    override fun goToShop() {
        binding.bottomNavigation.selectedItemId = R.id.menu_shop
    }

    override fun openProductDetail(productId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ProductDetailFragment.newInstance(productId))
            .addToBackStack(ProductDetailFragment.TAG)
            .commit()
    }

    private fun replaceFragment(fragment: Fragment): Boolean {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        return true
    }

    companion object {
        private const val TAG = "LIFE_QUIZ"
    }
}

package com.example.week2
import com.example.week2.databinding.ActivityMainBinding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private val TAG = "LIFE_QUIZ"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.d(TAG, "onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        binding.bottomBarInclude.btnHome.setOnClickListener {
            replaceFragment(HomeFragment())
        }
        binding.bottomBarInclude.btnBuy.setOnClickListener {
            replaceFragment(BuyFragment())
        }
        binding.bottomBarInclude.btnWishlist.setOnClickListener {
            replaceFragment(WishlistFragment())
        }
        binding.bottomBarInclude.btnShoppingcart.setOnClickListener {
            replaceFragment(ShoppingcartFragment())
        }
        binding.bottomBarInclude.btnProfile.setOnClickListener {
            replaceFragment(ProfileFragment())
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

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
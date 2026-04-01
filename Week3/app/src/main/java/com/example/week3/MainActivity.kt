package com.example.week3

import com.example.week3.databinding.ActivityMainBinding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}